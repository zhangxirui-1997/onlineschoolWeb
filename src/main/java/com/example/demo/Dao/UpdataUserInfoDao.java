package com.example.demo.Dao;


import java.sql.Statement;

//ceshi
public class UpdataUserInfoDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public boolean upthisdata(String phonenumber,String fakename,String reallyname,
			String state,String school) throws Exception, Exception {
		String table="user_info_"+phonenumber.substring(phonenumber.length()-1);
		statement=(Statement) connect.begin();
		
		String sqlString="UPDATE "+table+" set user_fakename=\'"+fakename+"\',user_reallyname=\'"+reallyname+"\',"
				+ "user_state=\'"+state+"\',user_school=\'"+school+"\'"
				+ "WHERE user_phonenumber=\'"+phonenumber+"\'";
		System.out.print("���´���"+sqlString);
		statement.executeUpdate(sqlString);
		return true;
	}
}
