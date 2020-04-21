package com.example.demo.Dao;


import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {
	
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public JSONObject LoginAction(String phonenumber, String password)throws Exception {
		String table="user_info_"+phonenumber.substring(phonenumber.length()-1);
			
		statement=(Statement) connect.begin();
		String sqlString="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumber+"\'"+"and user_password="+"\'"+password+"\'";
		ResultSet rs=statement.executeQuery(sqlString);
		
		JSONObject jsonObject=new JSONObject();
		
		if(rs.next()){
			
			System.out.println("登陆成功！"+phonenumber+"欢迎你");
            String sqlString2 ="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumber+"\'";
            ResultSet rs2=statement.executeQuery(sqlString2);
			System.out.println(rs2.toString());
			rs2.next();
            jsonObject.put("user_phonenumber", phonenumber);
            jsonObject.put("user_fakename", rs2.getString("user_fakename"));
            System.out.print(rs2.getString("User_fakename"));
            jsonObject.put("user_reallyname", rs2.getString("user_reallyname"));
            jsonObject.put("user_state", rs2.getString("user_state"));
            jsonObject.put("user_sex", rs2.getString("user_sex"));
            jsonObject.put("user_brithday", rs2.getString("user_brithday"));
            jsonObject.put("user_school", rs2.getString("user_school"));
            jsonObject.put("user_moneybag", rs2.getString("user_moneybag"));
            
            
            
            rs2.close();
            rs.close();
            return jsonObject;
        }
        else{
        	System.out.println("账号密码错误");
        	return null;
        }
		
		
	}
	public JSONObject GetAction(String phonenumber)throws Exception {
		String table="user_info_"+phonenumber.substring(phonenumber.length()-1);
			
		statement=(Statement) connect.begin();
		String sqlString="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumber+"\'";
		ResultSet rs=statement.executeQuery(sqlString);
		
		JSONObject jsonObject=new JSONObject();
		
		if(rs.next()){
			
			System.out.println("登陆成功！"+phonenumber+"欢迎你");
            String sqlString2 ="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumber+"\'";
            ResultSet rs2=statement.executeQuery(sqlString2);
			System.out.println(rs2.toString());
			rs2.next();
            jsonObject.put("user_phonenumber", phonenumber);
            jsonObject.put("user_fakename", rs2.getString("user_fakename"));
            System.out.print(rs2.getString("User_fakename"));
            jsonObject.put("user_reallyname", rs2.getString("user_reallyname"));
            jsonObject.put("user_state", rs2.getString("user_state"));
            jsonObject.put("user_sex", rs2.getString("user_sex"));
            jsonObject.put("user_brithday", rs2.getString("user_brithday"));
            jsonObject.put("user_school", rs2.getString("user_school"));
            jsonObject.put("user_moneybag", rs2.getString("user_moneybag"));
            jsonObject.put("Invitation", rs2.getString("Invitation_code"));
            
            
            
            rs2.close();
            rs.close();
            return jsonObject;
        }
        else{
        	System.out.println("账号密码错误");
        	return null;
        }
		
		
	}

}
