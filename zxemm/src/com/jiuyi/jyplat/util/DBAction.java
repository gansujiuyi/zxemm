/**
 * <p>Title:             DBAction.java
 * <p>Description:       数据库基本操作的通用实现类
 * <p>Copyright:         Copyright (C), 2002-2003, UXUN Tech. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0 2002-12-09
 * @see		             com.jiuyi.util.DBAction
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/11/2002	          xuf	                             	         v1.0
 * 01/08/2003	          xuf	       将return语句从finally中移出       v1.1
 * 04/29/2003             xuf          首先捕获连接异常                 v1.2
 * 05/14/2003             xuf          加入查询返回对象集合 方法        v1.3
 * 05/15/2003             xuf          加入插入和更新对象方法          v1.4
 * 07/16/2003             zhangzh      使用不多种连接池来回切换: tomcat 和 BCMSDBConnPool          v1.4
 */

package com.jiuyi.jyplat.util;

import java.sql.*;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.lang.reflect.*;
import java.util.LinkedList;
import com.jiuyi.jyplat.exception.MethodCallException;

/**
 * 实现基本的数据库操作,包括删除,更新以及查询
 * 若为批量查询,则不包括在此类中.
 * 批量分页查询的实现见com.jiuyi.util包中的Page(PageToObj) , PageControl类.
 *
 * 被调用者: 具体的商业实现者
 */
@Repository
public class DBAction {
	boolean connSwitch = true;//true 为tomcat 的连接池,false 为BCMSDBConnPool连接池

	Logger Log = Logger.getLogger(DBAction.class);

	public static final String CONNECTION_ERROR = "[CONNECTION REFUSED]";

	public DBAction() {
	}

	/**
	 *Function: executeUpdate
	 *Description: 执行数据库的单个SQL语句更新操作
	 *@parameter update的sql语句
	 *@return 操作成功标志
	 */
	public int executeUpdate(String updateSql) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		int flag = -99;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			stmt = con.prepareStatement(updateSql);
			int count = stmt.executeUpdate();
			if (count == 0) {
				flag = -1;
			}
			flag = 0;
		} catch (Exception e) {
			con.rollback();
			flag = -1;
			Log.error(e.toString());
			throw new Exception("数据库更新操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				// else
				//   bcmsPool.freeConn("DBPool",con);
			}
		}
		return flag;
	}

	/**
	* 将传入的数据对象更新到数据库中
	*@param Object 数据Bean
	*@param String 进行数据更新操作的表名
	*@param String where 定位语句, 形式如  CUST_NO = '111', 注:不须要前加WHERE
	*@return void
	*/
	public void executeUpdate(Object dataBean, String tableName, String whereClause) throws Exception {
		//得到传入数据对象的所有属性值
		Field[] field = dataBean.getClass().getDeclaredFields();
		String[] fieldName = new String[field.length];
		for (int i = 0; i < field.length; i++) {
			fieldName[i] = field[i].getName();
		}

		//生成预编译SQL CLAUSE
		StringBuffer insertSql = new StringBuffer("UPDATE ");
		insertSql.append(tableName);
		insertSql.append(" SET ");
		for (int i = 0; i < fieldName.length; i++) {
			insertSql.append(fieldName[i].toUpperCase());
			insertSql.append("=?,");
		}
		insertSql.deleteCharAt(insertSql.length() - 1); //删除最后的","
		insertSql.append(" WHERE ");
		insertSql.append(whereClause);

		Log.debug(insertSql.toString());

		//建立连接
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}
		pstmt = con.prepareStatement(insertSql.toString());
		setValue(pstmt, dataBean, field, fieldName); //执行PreparedStatement的set操作

		try {
			pstmt.executeUpdate();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception("数据库更新操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
	}

	/**
	* 将传入的数据对象写入数据库
	*@param Object 数据Bean
	*@param String 进行数据插入操作的表名
	*@return void
	*/
	public void executeInsert(Object dataBean, String tableName) throws Exception {
		//得到传入数据对象的所有属性值
		Field[] field = dataBean.getClass().getDeclaredFields();
		String[] fieldName = new String[field.length];
		for (int i = 0; i < field.length; i++) {
			fieldName[i] = field[i].getName();
		}

		//生成预编译SQL CLAUSE
		StringBuffer insertSql = new StringBuffer("INSERT INTO ");
		insertSql.append(tableName);
		insertSql.append("(");
		for (int i = 0; i < fieldName.length; i++) {
			insertSql.append(fieldName[i].toUpperCase());
			insertSql.append(",");
		}
		insertSql.setCharAt(insertSql.length() - 1, ')'); //替换最后的","
		insertSql.append("VALUES (");
		for (int i = 0; i < fieldName.length; i++) {
			insertSql.append("?,");
		}
		insertSql.setCharAt(insertSql.length() - 1, ')'); //替换最后的","

		//方便调试
		Log.debug(insertSql.toString());

		//建立连接
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}
		pstmt = con.prepareStatement(insertSql.toString());
		setValue(pstmt, dataBean, field, fieldName); //执行PreparedStatement的set操作

		try {
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.toString());
			throw new Exception("数据库插入操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				// else
				//   bcmsPool.freeConn("DBPool",con);
			}
		}
	}

	/**
	 *Function: executeUpdate (overloading method)
	 *Description:
	 *  执行数据库的多个SQL语句更新操作
	 *  如果其中任一个SQL语句执行不成功, 全部 roll back
	 *@parameter update语句数组
	 *@return 操作成功标志
	 */
	public int executeUpdate(String[] updateSql) throws Exception {
		Connection con = null;
		Statement stmt = null;
		int flag = -99;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();

			// added
			stmt.clearBatch();

			for (int i = 0; i < updateSql.length; i++) {
				if (updateSql[i] == null || updateSql[i].equals(""))
					continue;
				stmt.addBatch(updateSql[i]);
			}

			stmt.executeBatch();

			con.commit();
			flag = 0;
		} catch (Exception e) {
			con.rollback();
			flag = -1;
			Log.error(e.toString());
			throw new Exception("数据库更新操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return flag;
	}

	/**
	 * 执行数据库的多个SQL语句更新操作(重载)
	 * 如果其中任一个SQL语句执行不成功, 全部 roll back
	 *
	 * @parameter update语句数组
	 * @return 操作成功标志
	 */
	public int executeUpdate(StringBuffer[] updateSql) throws Exception {
		Connection con = null;
		Statement stmt = null;
		int flag = -99;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.getMessage());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();

			for (int i = 0; i < updateSql.length; i++) {
				stmt.executeUpdate(updateSql[i].toString());
			}

			con.commit();
			flag = 0;
		} catch (Exception e) {
			con.rollback();
			flag = -1;
			Log.error(e.toString());
			throw new Exception("数据库更新操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return flag;
	}

	/**
	 *Function: executeDelete
	 *Description: 执行数据库的记录删除操作
	 *@parameter delete语句
	 *@return 操作成功标志
	 */
	public int executeDelete(String deleteSql) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		int flag = -99;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			stmt = con.prepareStatement(deleteSql);
			stmt.executeUpdate();

			flag = 0;
		} catch (Exception e) {
			con.rollback();
			flag = -1;
			Log.error(e.toString());
			throw new Exception("数据库删除操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return flag;
	}

	/**
	 *Function: executeSearch
	 *Description: 执行数据库的单个记录查询操作
	 *@parameter 查询语句
	 *@return 查询结果,以String数组形式表示,用户可对其进行二次封装
	 */
	public String[] executeSearch(String searchSql) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String[] data = null;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(searchSql);
			stmt = con.prepareStatement(searchSql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();//得到查询返回的列数
			if (rs.next()) {
				data = new String[cols];
				for (int i = 0; i < cols; i++) {
					data[i] = rs.getString(i + 1);
					if (data[i] == null)
						data[i] = "";
				}
			}
		} catch (Exception e) {
			con.rollback();
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}

		}
		return data;
	}

	/**
	 *Function: executeSearchAll
	 *Description: 执行数据库的多个记录查询操作
	 *@parameter 查询语句
	 *@return 查询结果,以String二维数组形式表示,用户可对其进行二次封装
	 */
	public String[][] executeSearchAll(String searchSql) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String[][] data = null;
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(searchSql);
			stmt = con.prepareStatement(searchSql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();//得到查询返回的列数
			int rows = 0;
			while (rs.next()) {
				rows++;
			} //得到查询返回的数据行数
			if (rows < 1) { //没有查到记录
				return null;
			}
			rs.close();
			//rs = stmt.executeQuery(searchSql);
			rs = stmt.executeQuery();
			data = new String[rows][]; //创建一个两维数组
			int curRow = 0;
			while (rs.next()) {
				data[curRow] = new String[cols];
				for (int i = 0; i < cols; i++) {
					data[curRow][i] = rs.getString(i + 1);
					if (data[curRow][i] == null)
						data[curRow][i] = "";
				}
				curRow++;
			}
		} catch (Exception e) {
			con.rollback();
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return data;
	}

	/**
	* 查询单条记录结果, 返回一个数据对象
	*   注: 该数据对象必须满足如下条件:
	*         1.该数据对象为映射表对象, 即对象属性应与表字段名一致
	*         2.该数据对象有空的构造函数
	*         3.具有getXX(), setXX()方法
	*@param String 查询的sql语句, 为静态sql语句
	*@param String 数据对象名称(包括包,如com.jiuyi.bcms.data.system.SysSubItem)
	*@return Object
	*/
	public Object executeSearch(String staticSql, String objectName) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Object obj = null;

		try {
			con = DBConnection.getConnection();
		} catch (Exception e) //取数据库连接产生例外
		{
			e.printStackTrace();
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(staticSql);
			pstmt = con.prepareStatement(staticSql);
			rs = pstmt.executeQuery();

			/* 取记录集的column名集合 */
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			LinkedList list = new LinkedList();
			for (int i = 1; i <= columnCount; i++) {
				list.add(rsmd.getColumnName(i).toUpperCase()); //转为大写
			}

			if (rs.next()) {
				obj = Class.forName(objectName).newInstance();
				setValue(obj, rs, list);
			} // end where
		} catch (MethodCallException e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			throw new Exception(e.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return obj;
	}

	/**
	* 查询所有结果, 返回一个数据对象集合
	*   注: 该数据对象必须满足如下条件:
	*         1.该数据对象为映射表对象, 即对象属性应与表字段名一致
	*         2.该数据对象有空的构造函数
	*         3.具有getXX(), setXX()方法
	*@param String 查询的sql语句, 为静态sql语句
	*@param String 数据对象名称(包括包,如com.jiuyi.bcms.data.system.SysSubItem)
	*@return Object[]
	*/
	public Object[] executeSearchAll(String staticSql, String objectName) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Object[] obj = null;

		try {
			con = DBConnection.getConnection();
		} catch (Exception e) //取数据库连接产生例外
		{
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(staticSql);
			pstmt = con.prepareStatement(staticSql);
			rs = pstmt.executeQuery();

			/* 取记录集的column名集合 */
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			LinkedList list = new LinkedList();
			for (int i = 1; i <= columnCount; i++) {
				if (Constant.SYS_DB_TYPE.trim().equalsIgnoreCase("db2"))
					list.add(rsmd.getColumnLabel(i).toUpperCase()); //转为大写
				else
					list.add(rsmd.getColumnName(i).toUpperCase()); //转为大写
			}

			/* 取返回的数据行数 */
			int rows = 0;
			while (rs.next()) {
				rows++;
			} //得到查询返回的数据行数
			if (rows < 1) { //没有查到记录
				return null;
			}
			rs.close();

			/* 往数据对象中赋值 */
			obj = new Object[rows];
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				obj[i] = Class.forName(objectName).newInstance();
				setValue(obj[i], rs, list);
				i++;
			} // end where
		} catch (MethodCallException e) {
			Log.error(e.getMessage());
			throw new Exception(e.toString());
		} catch (SQLException e) {
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				// else
				//   bcmsPool.freeConn("DBPool",con);
			}
		}
		return obj;
	}

	/**
	* 查询单条记录结果, 返回一个数据对象
	*   注: 该数据对象必须满足如下条件:
	*         1.该数据对象为映射表对象, 即对象属性应与表字段名一致
	*         2.该数据对象有空的构造函数
	*         3.具有getXX(), setXX()方法
	*@param String 查询的sql语句(预编译类型如:select a from t where b=?))
	*@param String[] 条件字段 ,对应sql语句中的?
	*@param String 数据对象名称(包括包,如com.jiuyi.bcms.data.system.SysSubItem)
	*@return Object
	*/
	public Object executeSearch(String preSql, String[] condition, String objectName) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Object obj = null;

		try {
			con = DBConnection.getConnection();
		} catch (Exception e) //取数据库连接产生例外
		{
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(preSql);
			pstmt = con.prepareStatement(preSql);
			for (int i = 0; i < condition.length; i++) {
				pstmt.setString(i + 1, condition[i]);
			}
			rs = pstmt.executeQuery();

			/* 取记录集的column名集合 */
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			LinkedList list = new LinkedList();
			for (int i = 1; i <= columnCount; i++) {
				list.add(rsmd.getColumnName(i).toUpperCase()); //转为大写
			}

			if (rs.next()) {
				obj = Class.forName(objectName).newInstance();
				setValue(obj, rs, list);
			} // end where
		} catch (MethodCallException e) {
			Log.error(e.getMessage());
			throw new Exception(e.toString());
		} catch (SQLException e) {
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				// else
				//   bcmsPool.freeConn("DBPool",con);
			}
		}
		return obj;
	}

	/**
	* 查询所有结果, 返回一个数据对象集合
	*   注: 该数据对象必须满足如下条件:
	*         1.该数据对象为映射表对象, 即对象属性应与表字段名一致
	*         2.该数据对象有空的构造函数
	*         3.具有getXX(), setXX()方法
	*@param String 查询的sql语句(预编译类型如:select a from t where b=?))
	*@param String[] 条件字段 ,对应sql语句中的?
	*@param String 数据对象名称(包括包,如com.jiuyi.bcms.data.system.SysSubItem)
	*@return Object[]
	*/
	public Object[] executeSearchAll(String sql, String[] condition, String objectName) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Object[] obj = null;

		try {
			con = DBConnection.getConnection();
		} catch (Exception e) //取数据库连接产生例外
		{
			Log.error(e.toString());
			throw new Exception(DBAction.CONNECTION_ERROR + e.getMessage());
		}

		try {
			Log.debug(sql);
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < condition.length; i++) {
				pstmt.setString(i + 1, condition[i]);
			}
			rs = pstmt.executeQuery();

			/* 取记录集的column名集合 */
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			LinkedList list = new LinkedList();
			for (int i = 1; i <= columnCount; i++) {
				list.add(rsmd.getColumnName(i).toUpperCase()); //转为大写
			}

			/* 取返回的数据行数 */
			int rows = 0;
			while (rs.next()) {
				rows++;
			} //得到查询返回的数据行数
			if (rows < 1) { //没有查到记录
				return null;
			}
			rs.close();

			/* 往数据对象中赋值 */
			obj = new Object[rows];
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				obj[i] = Class.forName(objectName).newInstance();
				setValue(obj[i], rs, list);
				i++;
			} // end where
		} catch (MethodCallException e) {
			Log.error(e.getMessage());
			throw new Exception(e.toString());
		} catch (SQLException e) {
			Log.error(e.toString());
			throw new Exception("数据库查询操作失败");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				if (connSwitch)
					con.close();
				//else
				//  bcmsPool.freeConn("DBPool",con);
			}
		}
		return obj;
	}

	//-------------- private method goes here ---------------------//
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
			/* 目前只支持String ,int ,double,long,Integer,Date类型, 可进行扩充 */
			if (columnList.contains(fieldName)) {
				methodName = "set" + oriFieldName.substring(0, 1).toUpperCase() + oriFieldName.substring(1);
				declareString = field[j].getType().getName();

				try {
					/* String类型,调用ResultSet的getString()方法 */
					if (declareString.equalsIgnoreCase("java.lang.String")) {
						holder = new ArgumentHolder();
						holder.setArgument((rs.getString(fieldName) == null) ? "" : (rs.getString(fieldName))); //if null, return ""
						MethodCall.execute(destObj, methodName, holder);
						//						Log.debug(fieldName+"="+rs.getString(fieldName)) ;
					}
					/* int类型,调用ResultSet的getInt()方法 */
					else if (declareString.equalsIgnoreCase("int")) {
						holder = new ArgumentHolder();
						holder.setArgument(rs.getInt(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//						Log.debug(fieldName+"="+String.valueOf( rs.getInt(fieldName) ) ) ;
					}
					/* double类型,调用ResultSet的getDouble()方法 */
					else if (declareString.equalsIgnoreCase("double")) {
						holder = new ArgumentHolder();
						holder.setArgument(rs.getDouble(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//						Log.debug(fieldName+"="+String.valueOf(rs.getDouble(fieldName)) ) ;
					} else if (declareString.equalsIgnoreCase("java.lang.Double")) {
						holder = new ArgumentHolder();
						holder.setArgument((Double) rs.getDouble(fieldName));
						MethodCall.execute(destObj, methodName, holder);
					} else if (declareString.equalsIgnoreCase("long")) {
						holder = new ArgumentHolder();
						holder.setArgument(rs.getLong(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//						Log.debug(fieldName+"="+String.valueOf(rs.getLong(fieldName)) ) ;
					} else if (declareString.equalsIgnoreCase("java.lang.Integer")) {
						holder = new ArgumentHolder();
						holder.setArgument((Integer) rs.getInt(fieldName));
						MethodCall.execute(destObj, methodName, holder);
						//						Log.debug(fieldName+"="+String.valueOf(rs.getLong(fieldName)) ) ;
					} else if (declareString.equalsIgnoreCase("java.util.Date")) {
						if (rs.getDate(fieldName) != null) {
							holder = new ArgumentHolder();
							holder.setArgument(new java.util.Date(rs.getDate(fieldName).getTime()));
							MethodCall.execute(destObj, methodName, holder);
						}
						//						Log.debug(fieldName+"="+String.valueOf(rs.getLong(fieldName)) ) ;
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

	/**
	* 从传入的数据对象中取出属性值并set到PreparedStatement对象中
	* 支持数据对象的String ,int , double类型
	*@param PreparedStatement 预编译statement对象, 由Connection.prepareStatement(String preSql)返回
	*@param Object 数据对象
	*@param Field[] 数据对象的Field对象集合
	*@param String 数据对象的属性名称集合
	*@return void
	*@throws MethodCallException 方法呼叫例外
	*@throws SQLException
	*/
	private void setValue(PreparedStatement pstmt, Object dataBean, Field[] field, String[] fieldName)
			throws MethodCallException, SQLException {
		String declareString; //定义属性类型
		String methodName; //定义属性的get方法
		Object getReturnValue; //调用数据Bean的get方法后的返回值
		ArgumentHolder holder; //入参封装对象
		for (int i = 0; i < fieldName.length; i++) {
			methodName = "get" + fieldName[i].substring(0, 1).toUpperCase() + fieldName[i].substring(1);
			declareString = field[i].getType().getName();
			Log.debug(fieldName[i]);
			try {
				/* String类型,调用ResultSet的getString()方法 */
				if (declareString.equalsIgnoreCase("java.lang.String")) {
					holder = new ArgumentHolder();
					getReturnValue = MethodCall.execute(dataBean, methodName, holder);
					if (getReturnValue == null) {
						pstmt.setString(i + 1, null);
						Log.debug(null);
					} else {
						pstmt.setString(i + 1, (String) getReturnValue);
						Log.debug((String) getReturnValue);
					}
				}
				/* int类型,调用ResultSet的getInt()方法 */
				else if (declareString.equalsIgnoreCase("int")) {
					holder = new ArgumentHolder();
					getReturnValue = MethodCall.execute(dataBean, methodName, holder);

					if (getReturnValue == null) {
						pstmt.setInt(i + 1, 0);
						Log.debug("0");
					} else {
						pstmt.setInt(i + 1, ((Integer) getReturnValue).intValue());
						Log.debug("" + ((Integer) getReturnValue).intValue());
					}
				}
				/* java.lang.Integer类型,调用ResultSet的getInt()方法 */
				else if (declareString.equalsIgnoreCase("java.lang.Integer")) {
					holder = new ArgumentHolder();
					getReturnValue = MethodCall.execute(dataBean, methodName, holder);

					if (getReturnValue == null) {
						pstmt.setInt(i + 1, 0);
						Log.debug("0");
					} else {
						pstmt.setInt(i + 1, ((Integer) getReturnValue).intValue());
						Log.debug("" + ((Integer) getReturnValue).intValue());
					}
				}
				/* double类型,调用ResultSet的getDouble()方法 */
				else if (declareString.equalsIgnoreCase("double")) {
					holder = new ArgumentHolder();
					getReturnValue = MethodCall.execute(dataBean, methodName, holder);

					if (getReturnValue == null) {
						pstmt.setDouble(i + 1, 0.0);
						Log.debug("0.0");
					} else {
						pstmt.setDouble(i + 1, ((Double) getReturnValue).doubleValue());
						Log.debug("" + ((Double) getReturnValue).doubleValue());
					}
				} else if (declareString.equalsIgnoreCase("long")) {
					holder = new ArgumentHolder();
					getReturnValue = MethodCall.execute(dataBean, methodName, holder);

					if (getReturnValue == null) {
						pstmt.setLong(i + 1, 0);
						Log.debug("0");
					} else {
						pstmt.setLong(i + 1, ((Long) getReturnValue).longValue());
						Log.debug("" + ((Long) getReturnValue).longValue());
					}
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

	//          public static void main(String[] args)
	//          {
	//            DBAction db = new DBAction();
	//
	//           System.out.println(db.howManyParams("?wewew?sdsds?"));
	//          }
}