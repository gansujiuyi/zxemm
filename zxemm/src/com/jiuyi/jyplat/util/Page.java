/**
 * <p>Title:             Page.java
 * <p>Description:       数据查询分页显示
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           Blueder
 * @author               xuf
 * @version	             v1.0 2002-12-06
 * @see		             com.blueder.util.Page
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/11/2002	          xuf	                             	         v1.0 
 */

package com.jiuyi.jyplat.util;

import java.sql.*;

import org.apache.log4j.Logger;

import com.jiuyi.net.socketProcThread;

/**
 * PersistenceLayer , used to deal with database
 * Two methods needed :
 *     getAvailableCount()  
 *     getResult()
 */
public class Page {
	Logger Log = Logger.getLogger(socketProcThread.class);

	private int columnCount; //查询的列数	  
	private int pageSize; //每页显示条数
	private int totalCount; //总行数
	private int curPage; //当前页码
	private String strSQL; //查询的sql语句
	private String[][] AllData; //查询页的数据显示

	/************* Constructor methods goes here *****************/
	/*@初始化@*/
	public Page(String selectSql, int pageSize, int curPage) throws Exception {

		setStrSQL(selectSql);
		setPageSize(pageSize);
		this.curPage = curPage;
		int i = goPage(); // main method		
	}

	public Page(String selectSql, int curPage) throws Exception {
		this(selectSql, 10, curPage);
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
	public String[][] getResult() {
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
		Statement stmt = null;
		ResultSet result = null;
		ResultSetMetaData rsmd;
		int rows = 0; //查询返回的数据行数
		int cols = 0; //查询返回的数据列数

		if (strSQL == null || strSQL.equals("")) {
			return -1;
		}//查询语句为空，返回null

		try {
			//System.out.println(strSQL);
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(strSQL);
			rsmd = result.getMetaData();
			cols = rsmd.getColumnCount();//得到查询返回的列数

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

			AllData = new String[pageSize][]; //创建一个两维数组
			int curRow = 0; //数组的当前行号
			int iloop = 0; //指针移动的次数

			result = stmt.executeQuery(strSQL);//重新查询一次
			while (result.next()) {

				if (curRow == pageSize)
					break; //取数据结束跳出				
				if (iloop < (curPage - 1) * pageSize) { //定位,继续搜寻;
				} else { //定位成功,开始读取数据
					AllData[curRow] = new String[columnCount]; //数组的数组
					for (int k = 0; k < columnCount; k++) {
						AllData[curRow][k] = result.getString(k + 1);
						if (AllData[curRow][k] == null)
							AllData[curRow][k] = "";
					}
					curRow++; //移动到下一行数据
				}//end else

				iloop++;
			}//end while 						
		} catch (Exception e) {
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
}
