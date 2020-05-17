package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;
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
		JSONObject jsonObject=new JSONObject();
		int i=2;
		try {
			i=replacePasswordDao.upthisdata(phonenumberstring, passWordstring);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) {
			jsonObject.put("one","yes");
		}else {
			jsonObject.put("one","no");
		}
		outPrintWriter.println(jsonObject);
		outPrintWriter.close();
	}

}
