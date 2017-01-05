package com.jiuyi.net.utils;

import org.hibernate.dialect.DB2Dialect;

public class UxunDB2Dialect extends DB2Dialect {

	private static boolean hasDistinct(String sql) {
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	/**
	 * Render the <tt>rownumber() over ( .... ) as rownumber_,</tt> 
	 * bit, that goes in the select list
	 */
	private String getRowNumber(String sql) {
		StringBuffer rownumber = new StringBuffer(50).append("rownumber() over(");

		int orderByIndex = sql.toLowerCase().indexOf("order by");

		if (orderByIndex > 0 && !hasDistinct(sql)) {
			rownumber.append(sql.substring(orderByIndex));
		}

		rownumber.append(") as rownumber_,");

		return rownumber.toString();
	}

	public String getLimitString(String sql, boolean hasOffset) {
		int startOfSelect = sql.toLowerCase().indexOf("select"); //获取第一个select的位置

		//======add by hwj on 2013/01/18======//
		String sqlRst = dealSql(sql);//最后要拼凑起来的字段名称
		//======add by hwj on 2013/01/18======//

		StringBuffer pagingSelect = new StringBuffer(2 * sql.length()).append(sql.substring(0, startOfSelect)) // add the comment

				//======add by hwj on 2013/01/18======//
				.append("select ").append(sqlRst).append(" from (")
				//======add by hwj on 2013/01/18======//

				.append("select * from ( select ") // nest the main query in an outer select
				.append(getRowNumber(sql)); // add the rownnumber bit into the outer query select list

		if (hasDistinct(sql)) {
			pagingSelect.append(" row_.* from ( ") // add another (inner) nested select
					.append(sql.substring(startOfSelect)) // add the main query
					.append(" ) as row_"); // close off the inner nested select
		} else {
			pagingSelect.append(sql.substring(startOfSelect + 6)); // add the main query
		}

		pagingSelect.append(" ) as temp_ where rownumber_ ");

		//add the restriction to the outer select
		if (hasOffset) {
			pagingSelect.append("between ?+1 and ?");
		} else {
			pagingSelect.append("<= ?");
		}

		//======add by hwj on 2013/01/18======//
		pagingSelect.append(" )");
		//======add by hwj on 2013/01/18======//

		return pagingSelect.toString();
	}

	/**
	 * ======add by hwj on 2013/01/18======//
	 * 由于Service类中的查询语句，可能不是封装成对象进行查询，而是直接通过select字段来查询数据，那么如果直接使用DB2Dialect方言，
	 * 将会多一个【rownumber_】列，此时，如果在页面上直接通过下标【0】【1】等取值，第0个数值将会是rownumber，而不是我们select的第一个字段，
	 * 因此，在这里，我们手工将hibernate自动生成的rownumber字段去掉
	 * @param sql 拼凑的字段
	 * @return
	 */
	private String dealSql(String sql) {
		int startOfSelect = sql.toLowerCase().indexOf("select"); //获取第一个select的位置
		int startOfFrom = sql.toLowerCase().indexOf("from"); //获取第一个from的位置

		//SELECT I.CUSTID  cid, I.CUSTNO AS CNO, I.SHORTNAME, I.CONTACTNAME, I.TELNO, I.CUSTACCNO, I.CUSTTYPE, I.STATUS FROM
		String ColumnStr = sql.substring(startOfSelect + 6, startOfFrom); //所有的字段名称

		String[] ColArray = ColumnStr.split(","); //以逗号为分隔符，对ColumnStr进行遍历
		String sqlRst = " ";//最后要拼凑起来的字段名称
		int indexAs = 0;//AS的位置
		int indexPoint = 0;//.的位置
		int indexSpace = 0;//空格的位置

		//遍历字段数组
		for (int i = 0; i < ColArray.length; i++) {

			indexAs = idxAsWord(ColArray[i].trim());

			//如果有AS，则直接取as后面的别名。如【I.CUSTNO AS CUSTNO】
			if (indexAs != -1)
				sqlRst += ColArray[i].trim().substring(indexAs + 3) + ", ";
			else {
				//如果没有AS，则取.后面的字符串
				indexPoint = idxPointWord(ColArray[i].trim());
				String tmp = ColArray[i].trim().substring(indexPoint + 1);
				//如果通过空格直接别名，则要取别名，同时，这样可以去掉distinct关键字。如【I.CUSTID  CUSTID】、【 DISTINCT I.CUSTID CUSTID】
				indexSpace = idxSpaceWord(tmp);

				if (indexSpace != -1)
					tmp = tmp.substring(indexSpace + 1);

				sqlRst += tmp + ", ";
			}
		}
		//拼凑结束后，去掉拼凑字符串的最后一个逗号
		sqlRst = sqlRst.substring(0, sqlRst.lastIndexOf(","));

		return sqlRst;
	}

	/**
	 * 返回传入的字符串包含as的位置
	 * @param str
	 * @return
	 */
	private static int idxAsWord(String str) {
		return str.toLowerCase().indexOf(" as ");
	}

	/**
	 * 返回传入的字符串包含.的位置
	 * @param str
	 * @return
	 */
	private static int idxPointWord(String str) {
		return str.toLowerCase().indexOf(".");
	}

	/**
	 * 返回传入的字符串包含空格的位置
	 * @param str
	 * @return
	 */
	private static int idxSpaceWord(String str) {
		return str.toLowerCase().indexOf(" ");
	}

}
