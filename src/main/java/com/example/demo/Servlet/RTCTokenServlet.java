package com.example.demo.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Tools.Sha256;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Servlet implementation class RTCTokenServlet
 */

//自习室通讯
@WebServlet("/RTCTokenServlet")
public class RTCTokenServlet extends HttpServlet {

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		//获取Calendar的实例，默认是当前的时间
		Calendar rightNow = Calendar.getInstance();
		//当前时间 加10天
		rightNow.add(Calendar.DAY_OF_YEAR, 10);
		//new SimgpleDateFormat 进行格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss", Locale.CHINA);
		//利用Calendar的getTime方法，将时间转化为Date对象
		Date date = rightNow.getTime();
		//利用SimpleDateFormat对象 把Date对象格式化
		String format = sdf.format(date);

		System.out.println("令牌结束时间1："+format);

		DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		long epoch=0;
		try {
			epoch = df.parse(format).getTime();
			System.out.println("unix时间："+epoch);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//需要输入三个参数，一个是手机号，另一个是频道号，还有一个是判断他是否是发起人
		String judgetString=request.getParameter("judge");
		int judge=Integer.valueOf(judgetString);
		String UserID=request.getParameter("phonenumber");
		String AppID="okveu70c";
		String Appkey="657262fa6f87412b229729c4a56a66d4";
		String Nonce="AK-2b9be4b25c2d38c409c376ffd2372be1";
		String Timestamp=epoch+"";

		String ChannelID=null;
		if (judge==1) {
			ChannelID="selfstudy"+epoch;
			System.out.println("申请新建的频道ＩＤ"+ChannelID);
			UserID="123456";
		}else if(judge==0) {
			ChannelID=request.getParameter("ChannelID");
			System.out.println("申请加入的频道ＩＤ"+ChannelID);
		}

		String Token=new Sha256().getSHA256(AppID+Appkey+ChannelID+UserID+Nonce+Timestamp);
		String[] GSLB=new String[] {"https://rgslb.rtc.aliyuncs.com"};

		JSONObject i=new JSONObject();
		i.put("AppID", AppID);
		i.put("Appkey",Appkey);
		i.put("ChannelID", ChannelID);
		i.put("UserID",UserID);
		i.put("Nonce", Nonce);
		i.put("Timestamp", Timestamp);
		i.put("Token",Token);
		i.put("GSLB",GSLB[0]);

		PrintWriter outPrintWriter=response.getWriter();

		outPrintWriter.println(i.toString());
		outPrintWriter.close();
	}
}
