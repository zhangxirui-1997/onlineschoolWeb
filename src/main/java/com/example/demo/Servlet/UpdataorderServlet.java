package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Dao.UpdataorderDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdataorderServlet")
public class UpdataorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		String phonenumber=request.getParameter("phonenumber");

		JSONArray i = null;
		try {
			i=new UpdataorderDao().Updataorder(phonenumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("查询订单，用户手机号："+phonenumber);

		response.setContentType("text/html;charset-UTF-8");

		PrintWriter outPrintWriter=response.getWriter();

		System.out.print("订单查询结束"+i.toString());
		outPrintWriter.println(i);
		outPrintWriter.close();
	}

}

