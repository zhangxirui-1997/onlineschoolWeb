package com.example.demo.Servlet;

import com.example.demo.Dao.ReplacePasswordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ReplacePassword
 */
@WebServlet("/ReplacePasswordServlet")
public class ReplacePasswordServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String phonenumberstring=request.getParameter("user_phonenumber");  
        String passWordstring=request.getParameter("user_password");
		
		PrintWriter outPrintWriter=response.getWriter();
		ReplacePasswordDao replacePasswordDao=new ReplacePasswordDao();
		 
		int i=2;
		try {
			i=replacePasswordDao.upthisdata(phonenumberstring, passWordstring);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) {
			outPrintWriter.write("yes");
		}else {
			outPrintWriter.write("no");
		}
		outPrintWriter.close();
	}

}
