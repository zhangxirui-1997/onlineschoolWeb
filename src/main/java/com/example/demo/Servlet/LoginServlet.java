package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		/*
		 * 鑾峰彇Android浼犺繃鏉ョ殑涓滆タ
		 * */
		String phonenumberstring=request.getParameter("user_phonenumber");  
        String passWordstring=request.getParameter("user_password");
        JSONObject i = null;
        //鍦ㄦ湇鍔″櫒绔В鍐充腑鏂囦贡鐮侀棶棰�   
        //userName=NewString.getNewString(userName);  
        //passWord=NewString.getNewString(passWord);  
        System.out.println("用户手机号"+phonenumberstring);
        System.out.println("用户密码"+passWordstring);
        
        response.setContentType("text/html;charset-UTF-8");
        
        PrintWriter outPrintWriter=response.getWriter();
        LoginDao thislogo = new LoginDao();
        
        try {
			i=thislogo.LoginAction(phonenumberstring, passWordstring);
			if(i!=null)System.out.println(i.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		 if(i!=null) {
			System.out.print("登录成功");
			outPrintWriter.println(i);
		 }else {
			System.out.print("登录失败");
			outPrintWriter.write("no");
		 }
		 outPrintWriter.close();
	}


}
