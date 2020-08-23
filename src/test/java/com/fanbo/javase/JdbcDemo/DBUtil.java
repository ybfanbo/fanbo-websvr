package com.fanbo.javase.JdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	// 驱动程序名
	private static String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名scutcs
	private static String url = "jdbc:mysql://192.168.1.201:3306/gaic";
	// MySQL配置时的用户名
	private static String user = "root";
	// Java连接MySQL配置时的密码
	private static String password = "028911";
	
	public static Connection getConnection() throws Exception{
		// 加载驱动程序
		Class.forName(driver);
		// 连续数据库
		Connection conn = DriverManager.getConnection(url, user, password);		
		return conn;
	}
	
	public static Statement getStatement(Connection conn) throws Exception{
		if (!conn.isClosed()) {
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			return statement;
		}else{
			return null;
		}		
	}
	
	public static ResultSet executeQuery(Connection conn, String sql) throws Exception{
		ResultSet rs = null;
		if (!conn.isClosed()) {
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();

			// 要执行的SQL语句
			rs = statement.executeQuery(sql);
		}		
		return rs;
	}
	
	public static int executeUpdate(Connection conn, String sql) throws Exception{
		Statement statement = conn.createStatement();
		int executeUpdate = statement.executeUpdate(sql);//返回0：执行失败，返回1：执行成功
		return executeUpdate;
	}
	

}
