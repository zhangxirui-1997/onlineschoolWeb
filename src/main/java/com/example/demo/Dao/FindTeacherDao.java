package com.example.demo.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FindTeacherDao {
	private Connect connect=new Connect();
	private Statement statement=null;

	public JSONArray FindTeacherAction(String gradestring) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String tablename="";
		if(gradestring.equals("高中")){
			tablename="teacher_high";
		}else if(gradestring.equals("初中")){
			tablename="teacher_junior";
		}else if(gradestring.equals("小学")){
			tablename="teacher_permary";
		}

		String sqlString2 ="select * from "+tablename;
		ResultSet rs2=statement.executeQuery(sqlString2);
		JSONArray jsonArray=new JSONArray();
		while (rs2.next()) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put( "teacher_number", rs2.getString("teacher_number"));
			jsonObject.put( "teacher_name", rs2.getString("teacher_name"));
			jsonObject.put( "teacher_introduce",  rs2.getString("teacher_introduce"));
			jsonObject.put( "teacher_light",  rs2.getString("teacher_light"));
			jsonObject.put( "teacher_time", rs2.getString("teacher_time"));
			jsonObject.put( "teacher_grade",rs2.getString("teacher_grade"));
			jsonObject.put( "teacher_statue", rs2.getString("teacher_statue"));
			jsonObject.put( "teacher_imgpath", rs2.getString("teacher_imgpath"));

			jsonArray.add(jsonObject);
		}
		if(jsonArray==null) {
			System.out.println("没有查到教师"+gradestring);
		}else {
			System.out.println("查到教师"+jsonArray.toString());
		}

		return jsonArray;

	}


}
