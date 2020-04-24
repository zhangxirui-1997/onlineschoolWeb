package com.example.demo.Servlet;

import com.example.demo.Dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 开发须知：本servlet用来完成 用户注册 功能
 * 输入的参数有两个：user_phonenumber（用户的手机号）、user_password（用户的密码）、用户的邀请码（string格式，可为空）
 * 返回的参数有一个：
 * 注册成功——返回“yes”（注册成功后要跳会登陆界面，让用户自行登陆，不可自动登陆）
 * 注册失败——返回“no”
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String phonenumberstring=request.getParameter("user_phonenumber");  
        String passWordstring=request.getParameter("user_password");
        String Invitation_code=request.getParameter("Invitation_code");
		//String phoneCode = request.getParameter("phone_code");

		int i = 0;
        //在服务器端解决中文乱码问题   
        //userName=NewString.getNewString(userName);  
        //passWord=NewString.getNewString(passWord);  
        System.out.println("注册账号："+phonenumberstring);
        System.out.println("注册密码："+passWordstring);
        System.out.println("邀请码"+Invitation_code);
		//System.out.println("验证码"+phoneCode);

        PrintWriter outPrintWriter=response.getWriter();
        RegisterDao thisregister = new RegisterDao();
        
        try {
			i=thisregister.registerup(phonenumberstring,passWordstring,Invitation_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 if(i==1) {
			 outPrintWriter.write("yes"); 
			 
		 }else if(i==2){ 
			 
			 outPrintWriter.write("no"); 
		 }else if(i==3) {
			 
			 outPrintWriter.write("no");
		 }
		 outPrintWriter.close();
	}
	

}
