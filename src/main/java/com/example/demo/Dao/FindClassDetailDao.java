package com.example.demo.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindClassDetailDao {
	private Connect connect=new Connect();
	private Statement statement=null;
	
	public JSONArray FindClassDetailAction(String gradestring,String numberstring) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String tablename="";
		if (gradestring.equals("高一")) {
			tablename="high_grade_1";
		}else if(gradestring.equals("高二")) {
			tablename="high_grade_2";
		}else if(gradestring.equals("高三")) {
			tablename="high_grade_3";
		}else if(gradestring.equals("初一")) {
			tablename="junior_grade_1";
		}else if(gradestring.equals("初二")) {
			tablename="junior_grade_2";
		}else if(gradestring.equals("初三")) {
			tablename="junior_grade_3";
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
		}
		String sqlString2 ="select * from "+tablename+" where class_number =\'"+numberstring+"\'";

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
			jsonObject.put("class_student_number", rs2.getString("class_student_number"));
			jsonObject.put("class_introduce", rs2.getString("class_introduce"));
			jsonObject.put("class_imgname",rs2.getString("class_imgname"));
			jsonObject.put("class_bookname",rs2.getString("class_bookname"));
			jsonObject.put("class_bookprice",rs2.getString("class_bookprice"));
			jsonObject.put("class_light", rs2.getString("class_light"));
			jsonObject.put("class_next_order", rs2.getString("class_next_order"));
			String next_class=rs2.getString("class_next_one_class");
			/*if(next_class.equals("")||next_class==null) {
				
			}else {*/
			
			Connect connect2=new Connect();
			Statement statement2=null;
			statement2=(Statement) connect2.begin();
			String sqlString4 ="select * from "+next_class+" where classdetail_number =\'"+numberstring+"\'";
			System.out.println("查询语句"+sqlString4);
			ResultSet rs4=statement2.executeQuery(sqlString4);
			JSONArray jsonArray4=new JSONArray();
			while (rs4.next()) {
				JSONObject jsonObject4=new JSONObject();
				jsonObject4.put("classdetail_name", rs4.getString("classdetail_name"));
				jsonObject4.put("classdetail_kind", rs4.getString("classdetail_kind"));
				jsonObject4.put("classdetail_state", rs4.getString("classdetail_state"));
				jsonObject4.put("classdetail_updata_time", rs4.getString("classdetail_updata_time"));
				jsonObject4.put("classdetail_time_length", rs4.getString("classdetail_time_length"));
				jsonObject4.put("classdetail_order_number", rs4.getString("classdetail_order_number"));
				jsonObject4.put("classdetail_path", rs4.getString("classdetail_path"));
				
				jsonArray4.add(jsonObject4);
			}
			System.out.println(jsonArray4.toString());
			jsonObject.put("class_detail", jsonArray4.toString());
				
			
			rs4.close();	
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	
	public Double FindClassPriceAction(String gradestring,String numberstring) throws SQLException, Exception {
		statement=(Statement) connect.begin();
		String tablename="";
		if (gradestring.equals("��һ")) {
			tablename="high_grade_1";
		}else if(gradestring.equals("�߶�")) {
			tablename="high_grade_2";
		}else if(gradestring.equals("����")) {
			tablename="high_grade_3";
		}else if(gradestring.equals("��һ")) {
			tablename="junior_grade_1";
		}else if(gradestring.equals("����")) {
			tablename="junior_grade_2";
		}else if(gradestring.equals("����")) {
			tablename="junior_grade_3";
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
		}
		Double priceDouble=0d;
		Double discountDouble=0d;
		String sqlString2 ="select * from "+tablename+" where class_number =\'"+numberstring+"\'";
		ResultSet rs2=statement.executeQuery(sqlString2);
		JSONArray jsonArray=new JSONArray();
		while (rs2.next()) {
			String price= rs2.getString("class_price");
			String discount=rs2.getString("class_discount");
			priceDouble=Double.parseDouble(price);
			discountDouble=Double.parseDouble(discount);
			if(discountDouble<1) {
				priceDouble=priceDouble*discountDouble;
			}else if(discountDouble>1) {
				priceDouble=priceDouble-discountDouble;
			}
			
			
			
		}
		return priceDouble;
	}
}
