package com.example.demo.Servlet;

import com.example.demo.Dao.EnsureDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class EnsureServlet
 */
@WebServlet("/EnsureServlet")
public class EnsureServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		String phonenumber=request.getParameter("phonenumber");  
		String key=request.getParameter("key");
		String judge=request.getParameter("judge");
        
		boolean result=false;
		
		try {
			result=new EnsureDao().b(phonenumber, key, judge);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        PrintWriter outPrintWriter=response.getWriter();
        
        String i="";
        if(result) {
        	i="yes";
        }else {
        	i="no";
        }
        
		outPrintWriter.println(i);
		outPrintWriter.close();
	}

}
