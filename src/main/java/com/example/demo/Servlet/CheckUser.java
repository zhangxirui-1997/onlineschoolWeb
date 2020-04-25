package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckUser")
public class CheckUser {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject i = null;

        //userName=NewString.getNewString(userName);
        //passWord=NewString.getNewString(passWord);
        response.setContentType("text/html;charset-UTF-8");

        PrintWriter outPrintWriter=response.getWriter();
        HttpSession session=request.getSession();
        i=(JSONObject) session.getAttribute("user");
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
