package com.example.demo.Dao;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public int registerup(String phonenumberstring, String passWordstring,String Invitation_code) throws Exception {
		statement=(Statement) connect.begin();
		String table="user_info_"+phonenumberstring.substring(phonenumberstring.length()-1);
		int i;
		
		String sqlString="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumberstring+"\'";
		ResultSet rs=statement.executeQuery(sqlString);
		if(rs.next()){
            System.out.println("用户已经存在！");
            i=3;
        }
        else{
        	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        	Date date = new Date(System.currentTimeMillis());
        	System.out.println(formatter.format(date)+"用户可以注册");
        	String sqlString2="insert into "+table+" values ("+"\'"+phonenumberstring+"\'"+",\'"+"随机的用户名"+
        			"\',\'"+"随机的真实名"+"\'"+",\'"+passWordstring+
        			"\',\'"+"未知"+"\'"+",\'"+"未知"+
        			"\',\'"+formatter.format(date)+"\'"+",\'"+"未知学校"+"\',\'0\',\'0\',\'"+Invitation_code+"\')";
        	System.out.println(sqlString2);
        	statement.executeUpdate(sqlString2);
        	i=1;
        }
		return i;
	}
}
