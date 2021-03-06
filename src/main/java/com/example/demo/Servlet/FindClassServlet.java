package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Dao.FindClassDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/FindClassServlet")
public class FindClassServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		String gradestring=request.getParameter("grade");  
        
        JSONArray i = null;

        System.out.println("查询课程所属年级"+gradestring);
        
        
        response.setContentType("text/html;charset-UTF-8");
        
        PrintWriter outPrintWriter=response.getWriter();
        FindClassDao thislogo = new FindClassDao();
        
        try {
			i=thislogo.FindClassAction(gradestring);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.print("查询课程结束:");
		outPrintWriter.println(i);
		outPrintWriter.close();
	}


}
