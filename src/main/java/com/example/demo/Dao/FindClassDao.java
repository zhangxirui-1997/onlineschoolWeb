package com.example.demo.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindClassDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public JSONArray FindClassAction(String gradestring) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String tablename="";
		if (gradestring.equals("��һ")) {
			tablename="high_grade_1";
		}else if(gradestring.equals("�߶�")) {
			tablename="high_grade_2";
		}else if(gradestring.equals("����")) {
			tablename="high_grade_3";
		}else if (gradestring.equals("����ר��")) {
			tablename="high_grade_special";
		}else  if(gradestring.equals("��һ")) {
			tablename="junior_grade_1";
		}else if(gradestring.equals("����")) {
			tablename="junior_grade_2";
		}else if(gradestring.equals("����")) {
			tablename="junior_grade_3";
		}else if(gradestring.equals("����")) {
			tablename="junior_grade_4";
		}else if (gradestring.equals("����ר��")) {
			tablename="junior_grade_special";
		}else if(gradestring.equals("һ�꼶")) {
			tablename="primary_grade_1";
		}else if(gradestring.equals("�����")) {
			tablename="primary_grade_2";
		}else if(gradestring.equals("�����")) {
			tablename="primary_grade_3";
		}else if(gradestring.equals("���꼶")) {
			tablename="primary_grade_4";
		}else if(gradestring.equals("���꼶")) {
			tablename="primary_grade_5";
		}else if(gradestring.equals("���꼶")) {
			tablename="primary_grade_6";
		}else if (gradestring.equals("Сѧר��")) {
			tablename="primary_grade_special";
		}
		
		String sqlString2 ="select * from "+tablename;
		ResultSet rs2=statement.executeQuery(sqlString2);
		JSONArray jsonArray=new JSONArray();
		while (rs2.next()) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put( "class_number", rs2.getString("class_number"));
			jsonObject.put( "class_big_title", rs2.getString("class_big_title"));
			jsonObject.put( "class_title",  rs2.getString("class_title"));
			jsonObject.put( "class_time",  rs2.getString("class_time"));
			jsonObject.put( "class_time_length", rs2.getString("class_time_length"));
			jsonObject.put( "class_teacher_one",rs2.getString("class_teacher_one"));
			jsonObject.put( "class_teacher_two", rs2.getString("class_teacher_two"));
			jsonObject.put( "class_howmanytimes", rs2.getString("class_howmanytimes"));
			jsonObject.put( "class_classification",rs2.getString("class_classification"));
			jsonObject.put( "class_grade",  rs2.getString("class_grade"));
			jsonObject.put( "class_price", rs2.getString("class_price"));
			jsonObject.put( "class_discount", rs2.getString("class_discount"));
			jsonObject.put( "class_state", rs2.getString("class_state"));
			jsonArray.add(jsonObject);
		}
		if(jsonArray==null) {
			System.out.println("û�в鵽�γ�");
		}else {
			System.out.println("�鵽�γ�"+jsonArray.toString());
		}
		
		return jsonArray;
		
	}

	
}
