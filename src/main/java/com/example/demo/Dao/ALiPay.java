package com.example.demo.Dao;

import java.sql.ResultSet;
import java.sql.Statement;

public class ALiPay {
	
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public int insertOrder(String classnumberstring,String phonestring,String ordertable,
			Double price,Double discount) throws Exception {
		statement=(Statement)connect.begin();
    	String sqlString2="INSERT INTO "+ ordertable +"( order_user_phone, order_class_number,order_price,order_discount,order_time,order_appraise_number,order_appraise_text )"
    			+ "VALUES (\'"+phonestring+"\',\'"+classnumberstring+"\',\'"+price+"\',\'"+discount+"\',\'δ��� \',\' \',\' \' )";
    	statement.executeUpdate(sqlString2,Statement.RETURN_GENERATED_KEYS);
    	System.out.println(sqlString2);
    	
    	ResultSet rs = statement.getGeneratedKeys(); 
    	rs.next();
    	System.out.println("�鵽����Ϊ:"+rs.getInt(1));
    	return rs.getInt(1);
	}
}
