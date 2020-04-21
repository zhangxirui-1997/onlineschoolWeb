package com.example.demo.Dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private java.sql.Connection conn=null;
	private String url;
	private Statement stmt;
	
	public Statement begin() throws SQLException, Exception{
		url="jdbc:mysql://123.57.235.123:3306/XiaoSchool?"
				+"user=XiaoSchool&password=EjSys2TKLmXGXppX&useUnicode=true&characterEncoding=UTF8&&useOldAliasMetadataBehavior=true";//闃叉涓枃鑰屽鑷寸殑涔辩爜
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("连接服务器");
		conn=DriverManager.getConnection(url);
		stmt=conn.createStatement();
		return stmt;
	}
	
}
