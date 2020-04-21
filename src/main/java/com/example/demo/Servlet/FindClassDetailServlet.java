package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Dao.FindClassDetailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/FindClassDetailServlet")
public class FindClassDetailServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String gradestring=request.getParameter("grade");  
		String numberstring=request.getParameter("number");
		
        
        JSONArray i = null;


        System.out.println("准备详细查询年级："+gradestring+"课程号"+numberstring);
        
        
        response.setContentType("text/html;charset-UTF-8");
        
        PrintWriter outPrintWriter=response.getWriter();
        FindClassDetailDao thislogo = new FindClassDetailDao();
        
        try {
			i=thislogo.FindClassDetailAction(gradestring,numberstring);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.print("详细查询结束,结果如下"+i.toString());
		outPrintWriter.println(i);
		outPrintWriter.close();
	}


}
