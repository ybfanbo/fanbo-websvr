package com.fanbo.javase.JdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseDemo {
	public static void main(String[] args) throws Exception {
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名scutcs
		String url = "jdbc:mysql://192.168.1.201:3306/gaic";
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "028911";

		// 加载驱动程序
		Class.forName(driver);
		// 连续数据库
		Connection conn = DriverManager.getConnection(url, user, password);
		if (!conn.isClosed()) {
			System.out.println("成功连接数据库....!");

			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			// 要执行的SQL语句
			String sql1 = "select * from hd_gaic_file_original where id in (3,5)";
			String sql2 = "update hd_gaic_file_original set owner = 'fanbo' where id = 9";
			ResultSet rs = statement.executeQuery(sql1);
			int executeUpdate = statement2.executeUpdate(sql2);
			System.out.println(executeUpdate);
			
			String fileName = null;  
			String userName = null;
			while(rs.next()) {
				fileName = rs.getString("filename");
				userName = rs.getString("username");
				System.out.println(fileName + "\t" + userName);
			}  
			rs.close();  
			conn.close();  			
		}
		
		
	}
}

