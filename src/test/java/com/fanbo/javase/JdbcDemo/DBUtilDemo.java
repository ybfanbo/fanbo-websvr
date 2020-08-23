package com.fanbo.javase.JdbcDemo;

import java.sql.Connection;
import java.sql.ResultSet;


public class DBUtilDemo {
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = DBUtil.executeQuery(conn, "select * from hd_gaic_file_original where id in(5, 3)");
		while(rs.next()){
			String string = rs.getString("username");
			
			System.out.println(string);
		}
		rs.close();
		conn.close();
	}
}
