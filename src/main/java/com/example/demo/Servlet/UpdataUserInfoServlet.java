package com.example.demo.Servlet;

import com.example.demo.Dao.UpdataUserInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class UpdataUserInfoServlet
 */
@WebServlet("/UpdataUserInfoServlet")
public class UpdataUserInfoServlet extends HttpServlet {
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		/*
		 * 获取Android传过来的东西
		 * */

		String phonenumber=request.getParameter("phonenumber");  
        String fakename=request.getParameter("fakename");
        String reallyname=request.getParameter("reallyname");  
        String state=request.getParameter("state");
        String school=request.getParameter("school"); 	
        
        
        try {
			boolean i=new UpdataUserInfoDao().upthisdata(phonenumber, fakename, reallyname, state, school);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        response.setContentType("text/html;charset-UTF-8");
        
        PrintWriter outPrintWriter=response.getWriter();
			
		outPrintWriter.write("yes");
		 
		outPrintWriter.close();
	}

}
