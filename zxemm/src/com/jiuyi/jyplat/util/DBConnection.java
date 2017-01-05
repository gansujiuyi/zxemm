/**
 * <p>Title:             DBConnection.java
 * <p>Description:       数据库连接对象
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0
 * @see		             com.jiuyi.util.DBConnection
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/11/2002	          xuf	                             	         v1.0
 * 07/16/2003             zhangzh      使用不多种连接池来回切换: tomcat 和 BCMSDBConnPool          v1.4
 */

package com.jiuyi.jyplat.util;

import com.jiuyi.jyplat.service.SpringContextUtil;
//import com.jiuyi.jyplat.util.UxunPlatProperties;
//import com.jiuyi.jyplat.util.NamingHelper ;
import javax.sql.*;
import java.sql.*;
//import javax.naming.NamingException ;
import org.apache.log4j.Logger;

/**
 * get db connection object
 * called directly by "DBConnection.getConnection()"
 */
public class DBConnection {
	//static int connType = UxunPlatProperties.DBPoolConnType ; //1--tomcat 2--BCMSDBConnPool 
	//3--Class.forName  在新的框架下取下该类方式
	static Logger Log = Logger.getLogger(DBConnection.class);

	/**
	 *static method , get a database connection object
	 *@return Connection
	 */
	public static Connection getConnection() {
		try {

			/*
			if(connType==3)
				{
			        //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			        //return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:bcms","bcms","bcms");
			        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			        Class.forName( UxunPlatProperties.strDBDriver ).newInstance();
			        return DriverManager.getConnection(UxunPlatProperties.strJDBCUrl , UxunPlatProperties.strDBUser , UxunPlatProperties.strDBUserPwd );
			        //return DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;databaseName=UXST;user=sa;password=hyhqyjczyzlj","sa","hyhqyjczyzlj");

				}
			        
			
			     if(connType==2){
			      UXUNDBConnPool bcmsPool = UXUNDBConnPool.newSingleton();
			      return bcmsPool.getConn("DBPool");
			    }
			   
			 */
			/* lookup object by jndi-name */

			//DataSource ds = (DataSource) NamingHelper.singleton().lookObject("jdbc/alipay") ;
			//DataSource ds = (DataSource) NamingHelper.singleton().lookObject( UxunPlatProperties.JNDINAME ) ;
			//获取SSH框架定义的数据库连接池连接
			DataSource ds = (DataSource) SpringContextUtil.getBean("dataSource");
			return ds.getConnection();
		} catch (Exception ex) {
			Log.error("系统错误 ， 取数据库连接失败！" + ex.getMessage());
		}
		return null;
	}

	public static Connection getDriverConn() {
		try {
			String user_pw = Systemparas.getUxunPara("SystemInit.db_user_pw");
			String db_uri = Systemparas.getUxunPara("SystemInit.db_uri");
			String driverString = "oracle.jdbc.driver.OracleDriver";
			String connString = "jdbc:oracle:thin:@";
			if (Constant.SYS_DB_TYPE.trim().equalsIgnoreCase("db2")) {
				driverString = "com.ibm.db2.jcc.DB2Driver";
				connString = "jdbc:db2://";
			}
			Class.forName(driverString).newInstance();
			Connection conn = DriverManager.getConnection(connString + db_uri, user_pw.split("/")[0],
					user_pw.split("/")[1]);
			return conn;
		} catch (Exception ex) {
			Log.error("系统错误 ， 实时取数据库连接失败！" + ex.getMessage());
		}
		return null;
	}
}