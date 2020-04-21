package com.example.demo.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnsureDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public boolean b(String phonenumber, String key,String judge) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String table="user_info_"+phonenumber.substring(phonenumber.length()-1);
		if(judge.equals("0")) {//��һ�α��
			String sqlString2="update "+table+" set keynow=\'"+key+"\' where user_phonenumber=\'"+phonenumber+"\'";
			statement.executeUpdate(sqlString2);
			
		}else if(judge.equals("1")) {//��֤
			String sqlString="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumber+"\'";
			ResultSet rs2=statement.executeQuery(sqlString);
			rs2.next();
			if(key.equals(rs2.getString("keynow"))) {
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
	
}
