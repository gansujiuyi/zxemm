/**
 * <p>Title:             PageToObj.java
 * <p>Description:       数据查询分页显示(数据对象以对象集合方式显示)
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           Blueder
 * @author               xuf
 * @version	             v1.0 2003-05-13
 * @see		             com.blueder.util.PageToObj
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 05/13/2003	          xuf	                             	         v1.0
 */

package com.jiuyi.jyplat.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.jiuyi.jyplat.exception.MethodCallException;

/**
 * PersistenceLayer , used to deal with database
 * Two methods needed :
 *     getAvailableCount()
 *     getResult()
 */
public class PageToObj {
	Logger Log = Logger.getLogger(PageToObj.class);

	private int columnCount; //查询的列数
	private int pageSize; //每页显示条数
	private int totalCount; //总行数
	private int curPage; //当前页码
	private String strSQL; //查询的sql语句
	private Object[] AllData; //查询页的数据显示(对象集合方式)
	private String objectName; //数据对象名程(包括完整包路径)

	/************* Constructor methods goes here *****************/
	/*@初始化@*/
	/**
	* 分页查询, 返回一个数据对象集合
	*   注: 该数据对象必须满足如下条件:
	*         1.该数据对象为映射表对象, 即对象属性应与表字段名一致
	*         2.该数据对象有空的构造函数
	*         3.具有getXX(), setXX()方法
	*@param String 查询的sql语句
	*@param int 当前页面
	*@param String 数据对象名称(包括包,如com.blueder.bcms.data.system.SysSubItem)
	*/
	public PageToObj(String selectSql, int pageSize, int curPage, String objectName) throws Exception {
		setObjectName(objectName);
		setStrSQL(selectSql);
		setPageSize(pageSize);
		this.curPage = curPage;
		int i = goPage(); // main method
	}

	//构造函数,缺省页面记录数
	public PageToObj(String selectSql, int curPage, String objectName) throws Exception {
		this(selectSql, 10, curPage, objectName);
	} //默认每页10行

	/************* getter methods goes here *****************/
	/**
	*@getter方法
	*取每页显示条数
	*/
	public int getPageSize() {
		return pageSize;
	}

	/**
	*@getter方法
	*取总行数
	*/
	public int getAvailableCount() {
		return totalCount;
	}

	/**
	*@getter方法
	*取当前页码
	*/
	public int getCurPage() {
		return curPage;
	}

	/**
	*@getter方法
	*取该页数据
	*/
	public Object[] getResult() {
		return AllData;
	}

	/************* setter methods goes here *****************/
	/**
	*@setter方法
	*设置每页显示记录数
	*/
	public void setPageSize(int n) {
		if (n > 0) {
			pageSize = n;
		} else {
			pageSize = 10;
		}
	}

	/**
	*@setter方法
	*设置查询SQL语句
	*/
	public void setStrSQL(String sql) {
		strSQL = sql;
	}

	/**
	*@setter方法
	*设置数据对象名称
	*/
	public void setObjectName(String name) {
		objectName = name;
	}

	/************* main methods goes here *****************/
	/**
	 *Function: goPage
	 *Description: 主要执行SQL语句,取得总行数,列数以及查询数据
	 *Calls:
	 *Called By:
	 *Table Accessed:
	 *Table Updated:
	 *@return int 标识
	 */
	public int goPage() throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		ResultSetMetaData rsmd;
		int rows = 0; //查询返回的数据行数
		int cols = 0; //查询返回的数据列数

		if (strSQL == null || strSQL.equals("")) {
			return -1;
		}//查询语句为空，返回null

		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(strSQL);
			result = stmt.executeQuery();

			/* 取记录集的column名集合 */
			rsmd = result.getMetaData();
			cols = rsmd.getColumnCount(); //得到查询返回的列数
			LinkedList list = new LinkedList();
			for (int i = 1; i <= cols; i++) {
				list.add(rsmd.getColumnName(i).toUpperCase()); //转为大写
			}

			while (result.next()) {
				rows++;
			}//得到查询返回的数据行数

			result.close();

			if (rows < 1 || cols < 1) {
				return -2;
			} //没有检索到数据

			//如果正确则得到结果
			totalCount = rows; //取总行数
			columnCount = cols; //取列数

			AllData = new Object[pageSize]; //创建返回对象集合

			int curRow = 0; //数组的当前行号
			int iloop = 0; //指针移动的次数

			result = stmt.executeQuery();
			while (result.next()) {
				if (curRow == pageSize)
					break; //取数据结束跳出
				if (iloop < (curPage - 1) * pageSize) { //定位,继续搜寻;
				} else {
					//定位成功,开始读取数据
					AllData[curRow] = Class.forName(objectName).newInstance();
					setValue(AllData[curRow], result, list);
					curRow++;

				}//end else

				iloop++;
			}//end while
		} catch (MethodCallException e) {
			throw new Exception(e.toString());
		} catch (SQLException e) {
			Log.error(e.getMessage() + ": " + strSQL);
			throw new Exception("数据库查询操作失败");
		} finally {
			/* close object */
			try {
				if (result != null)
					result.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e1) {
				throw new Exception("close connection exception form Page.java");
			}
		}
		return 0;
	}

	/**
	* 从返回记录集中取得结果并赋值给数据对象
	* 支持数据对象的String ,int , double类型
	*@param Object 数据对象
	*@param ResultSet 记录集
	*@param LinkedList 记录集中column名集合
	*@return void
	*@throws MethodCallException 方法呼叫例外
	*@throws SQLException
	*/
	private void setValue(Object destObj, ResultSet rs, LinkedList columnList) throws MethodCallException, SQLException {
		/* 取data bean的所有属性名称 */
		Field[] field = destObj.getClass().getDeclaredFields();

		String oriFieldName; //在对象中定义的属性
		String fieldName; //将属性转为大写

		String methodName; //该属性的setXX方法名
		String declareString; //属性的申明类型

		ArgumentHolder holder; //定义入参封装对象

		/* 遍历属性并赋值 */
		for (int j = 0; j < field.length; j++) {
			oriFieldName = field[j].getName(); //取属性字符串
			fieldName = oriFieldName.toUpperCase(); //转为大写

			/* 当记录集的列名中包含该属性时,将对应数据赋给数据对象 */
			/* 目前只支持String ,int ,double类型, 可进行扩充 */
			if (columnList.contains(fieldName)) {
				methodName = "set" + oriFieldName.substring(0, 1).toUpperCase() + oriFieldName.substring(1);
				declareString = field[j].getType().getName();
				
				try {
					//Log.debug( fieldName+ "=" + rs.getString(fieldName) ) ;
					/* String类型,调用ResultSet的getString()方法 */
					if (declareString.equalsIgnoreCase("java.lang.String")) {
						holder = new ArgumentHolder();
						holder.setArgument((rs.getString(fieldName) == null) ? "" : (rs.getString(fieldName))); //if null, return ""
						MethodCall.execute(destObj, methodName, holder);
					} else if (declareString.equalsIgnoreCase("int")) {
						/* int类型,调用ResultSet的getInt()方法 */
						holder = new ArgumentHolder();
						holder.setArgument(rs.getInt(fieldName));
						MethodCall.execute(destObj, methodName, holder);
					} else if (declareString.equalsIgnoreCase("java.lang.Integer")) {
						/* java.lang.Integer类型,调用ResultSet的getInt()方法 */
						holder = new ArgumentHolder();
						holder.setArgument((Integer) rs.getInt(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//Log.debug(fieldName + "=" + String.valueOf(rs.getDouble(fieldName)));
					} else if (declareString.equalsIgnoreCase("java.lang.Double")) {
						/* java.lang.Double类型,调用ResultSet的getDouble()方法 */
						holder = new ArgumentHolder();
						holder.setArgument((Double) rs.getDouble(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//Log.debug(fieldName + "=" + String.valueOf(rs.getDouble(fieldName)));
					} else if (declareString.equalsIgnoreCase("java.math.BigDecimal")) {
						holder = new ArgumentHolder();
						BigDecimal value = rs.getBigDecimal(fieldName);
						if(value != null){
							holder.setArgument(value);
							MethodCall.execute(destObj, methodName, holder);
						}
					} else if (declareString.equalsIgnoreCase("double")) {
						/* double类型,调用ResultSet的getDouble()方法 */
						holder = new ArgumentHolder();
						holder.setArgument(rs.getDouble(fieldName));
						MethodCall.execute(destObj, methodName, holder);
					} else if (declareString.equalsIgnoreCase("long")) {
						holder = new ArgumentHolder();
						holder.setArgument(rs.getLong(fieldName));
						MethodCall.execute(destObj, methodName, holder);
					} else if (declareString.equalsIgnoreCase("java.lang.StringBuffer")) { //处理从数据库取时间的操作
						holder = new ArgumentHolder();
						holder.setArgument(new StringBuffer(rs.getDate(fieldName) + " " + rs.getTime(fieldName)));
						MethodCall.execute(destObj, methodName, holder);
					}

				} catch (NoSuchMethodException e) {
					Log.error(e.getMessage());
					throw new MethodCallException(e);
				} catch (InvocationTargetException e) {
					Log.error(e.getMessage());
					throw new MethodCallException(e);
				} catch (IllegalAccessException e) {
					Log.error(e.getMessage());
					throw new MethodCallException(e);
				}
			}
		}
	}
}
