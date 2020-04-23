package com.example.demo.Servlet;

import com.example.demo.Tools.destPhoneUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Classname ObtainPhoneCodeServlet
 * @Date 2020/4/23
 */

@WebServlet("/ObtainPhoneCodeServlet")
public class ObtainPhoneCodeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取注册手机号
        String phonenumberstring = request.getParameter("user_phonenumber");
        //请求验证码  发送到手机
        String phoneCode = destPhoneUtil.phone(phonenumberstring);

        //该方法是将后台的json值传输到前台
        response.getWriter().write(phoneCode);
    }
}
