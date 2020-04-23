package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Dao.FindClassDetailDao;
import com.example.demo.Dao.QianbaoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class QianbaoServlet
 */
@WebServlet("/QianbaoServlet")
public class QianbaoServlet extends HttpServlet {
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		String phonenumber=request.getParameter("phonenumber");
		String classnumber=request.getParameter("classnumber");
		String ordertable=request.getParameter("ordertable");
		String pricenumber=request.getParameter("price");
		String grade=request.getParameter("grade");

		String address_string=request.getParameter("address_string");
		String phone_string=request.getParameter("phone_string");
		String invitation_string=request.getParameter("invitation_string");


		System.out.println("订单信息"+phonenumber+" "+classnumber+" "+ordertable+" "+grade);

		response.setContentType("text/html;charset-UTF-8");

		PrintWriter outPrintWriter=response.getWriter();

		Double mynum=0d;

		try {
			mynum=new QianbaoDao().mynumaction(phonenumber);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Double price=Double.parseDouble(pricenumber);
		if(mynum<price) {//钱包的钱不够
			outPrintWriter.write("no");
		}else {
			mynum=mynum-price;
			JSONArray jsonArray=null;
			try {
				new QianbaoDao().updatanumber(phonenumber,mynum+"");
				new QianbaoDao().insertOrder(classnumber, phonenumber, ordertable, pricenumber,address_string,phone_string,invitation_string);
				jsonArray=new FindClassDetailDao().FindClassDetailAction(grade,classnumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outPrintWriter.write(jsonArray.toString());
		}





		outPrintWriter.close();
	}

}

