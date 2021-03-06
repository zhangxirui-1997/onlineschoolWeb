package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckUser")
public class CheckUser extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         JSONObject i = null;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset-UTF-8");
/*
*
* 获取session的信息
* */
        PrintWriter outPrintWriter=response.getWriter();
        HttpSession session=request.getSession();
        i=(JSONObject) session.getAttribute("user");
        if(i!=null) {
            System.out.print("登录成功");
            outPrintWriter.println(i);
        }else {
            System.out.print("登录失败");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("one","no");
            outPrintWriter.println(jsonObject);
        }
        outPrintWriter.close();
    }
}
