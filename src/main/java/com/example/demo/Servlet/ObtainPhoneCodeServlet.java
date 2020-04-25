package com.example.demo.Servlet;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.demo.Tools.destPhoneUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Classname ObtainPhoneCodeServlet
 * @Date 2020/4/23
 */

@WebServlet("/ObtainPhoneCodeServlet")
public class ObtainPhoneCodeServlet extends HttpServlet {
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取注册手机号
        String phonenumberstring = request.getParameter("user_phonenumber");
        //请求验证码  发送到手机
        String phoneCode = destPhoneUtil.phone(phonenumberstring);
        //String phoneCode = "123456";

        //返回给前端数据
        PrintWriter out = response.getWriter();
        out.print(phoneCode);
        out.flush();
        out.close();
    }
}