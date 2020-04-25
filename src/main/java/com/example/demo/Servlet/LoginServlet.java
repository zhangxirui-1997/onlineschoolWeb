package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/*
* 开发须知：本servlet用来完成 用户登陆 功能
* 输入的参数有两个：user_phonenumber（用户的手机号）、user_password（用户的密码）
* 返回的参数有一个：
* 登陆成功——返回用户信息JSONObject。
* 		   包括user_phonenumber、user_fakename（用户昵称）、user_reallyname（用户真实名字）、
* 			  user_state（用户地区）、user_moneybag（用户的钱包），其他的不用管
*
* 登陆失败——返回“no”
* */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		String phonenumberstring=request.getParameter("user_phonenumber");  
        String passWordstring=request.getParameter("user_password");
        JSONObject i = null;

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
        	/*
        	*
        	* 将登陆信息存进session
        	* */
			 HttpSession session=request.getSession();
			 session.setAttribute("user",i);
			System.out.print("登录成功");
			outPrintWriter.println(i);
		 }else {
			System.out.print("登录失败");
			outPrintWriter.write("no");
		 }
		 outPrintWriter.close();
	}


}
