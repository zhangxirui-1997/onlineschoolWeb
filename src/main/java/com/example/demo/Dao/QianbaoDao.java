package com.example.demo.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QianbaoDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	public Double mynumaction(String phonenumberstring) throws Exception {
		statement=(Statement) connect.begin();
		String table="user_info_"+phonenumberstring.substring(phonenumberstring.length()-1);
		int i;
		
		String sqlString="select * from "+table+" WHERE user_phonenumber= "+"\'"+phonenumberstring+"\'";
		ResultSet rs=statement.executeQuery(sqlString);
		rs.next();
		System.out.println("hello"+rs.getString(9));
		return Double.parseDouble(rs.getString(9));
	}
	
	public void updatanumber(String phonenumberstring,String number) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String table="user_info_"+phonenumberstring.substring(phonenumberstring.length()-1);
		String sqlString="UPDATE "+table+" set user_moneybag=\'"+number+"\'"
				+ "WHERE user_phonenumber=\'"+phonenumberstring+"\'";
		System.out.print(sqlString);
		statement.executeUpdate(sqlString);
	}
	
	public void insertOrder(String classnumberstring,String phonestring,String ordertable,
			String price,String address_string,String phone_string,String invitation_string) throws Exception {
		statement=(Statement)connect.begin();
    	String sqlString2="INSERT INTO "+ ordertable +"( order_user_phone, order_class_number,order_price,order_discount,order_time,order_appraise_number,order_appraise_text,Invitation_code,post_phone,post_address )"
    			+ "VALUES (\'"+phonestring+"\',\'"+classnumberstring+"\',\'"+price+"\',\'1\',\'完成\',\' \',\' \',\'"+invitation_string+"\',\'"+phone_string+"\',\'"+address_string+"\' )";
    	System.out.println(sqlString2);
    	statement.executeUpdate(sqlString2);
	}
}
