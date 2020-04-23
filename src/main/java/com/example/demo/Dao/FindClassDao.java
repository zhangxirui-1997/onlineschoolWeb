package com.example.demo.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FindClassDao {
	private Connect connect=new Connect();
	private Statement statement=null;

	public JSONArray FindClassAction(String gradestring) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String tablename="";
		if (gradestring.equals("高一")) {
			tablename="high_grade_1";
		}else if(gradestring.equals("高二")) {
			tablename="high_grade_2";
		}else if(gradestring.equals("高三")) {
			tablename="high_grade_3";
		}else if (gradestring.equals("高中专题")) {
			tablename="high_grade_special";
		}else  if(gradestring.equals("初一")) {
			tablename="junior_grade_1";
		}else if(gradestring.equals("初二")) {
			tablename="junior_grade_2";
		}else if(gradestring.equals("初三")) {
			tablename="junior_grade_3";
		}else if(gradestring.equals("初四")) {
			tablename="junior_grade_4";
		}else if (gradestring.equals("初中专题")) {
			tablename="junior_grade_special";
		}else if(gradestring.equals("一年级")) {
			tablename="primary_grade_1";
		}else if(gradestring.equals("二年纪")) {
			tablename="primary_grade_2";
		}else if(gradestring.equals("三年纪")) {
			tablename="primary_grade_3";
		}else if(gradestring.equals("四年级")) {
			tablename="primary_grade_4";
		}else if(gradestring.equals("五年级")) {
			tablename="primary_grade_5";
		}else if(gradestring.equals("六年级")) {
			tablename="primary_grade_6";
		}else if (gradestring.equals("小学专题")) {
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
			System.out.println("没有查到课程");
		}else {
			System.out.println("查到课程"+jsonArray.toString());
		}

		return jsonArray;

	}


}
