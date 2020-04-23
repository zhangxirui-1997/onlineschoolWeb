package com.example.demo.Servlet;

import com.example.demo.Dao.ALiPay;
import com.example.demo.Dao.FindClassDetailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class OrderInfo
 */
@WebServlet("/OrderInfoServlet")
public class OrderInfoServlet extends HttpServlet {
	private String URL="https://openapi.alipay.com/gateway.do";
	private String API_ID="2021001140678754";
	private String APP_PRIVATE_KEY="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCfJaSumIDOY5lf4WyyHsx+sMt0WH8oanLTE3D0V4SpzTkGf7Ms9MhlrH4SLKQ5n7FUDmkG0BO1K9C0UwSlQexaNBvsAC8Cnmpa9iGI6uiFNdueWPA0eTtFFvDpwzfeAbjuGFDU67KQv4eX7I6CwPj2r50SG18942aW3gYlYAcoctgKAqSqrJ27xs4T9/WDQwWEDCQlcbjl5JPQqtf/XoqhrnUE8inTvSC8ZV7+9b+0PWnsuPly6nmUj2AH+N4QEDqyObnYYmZM2RL209u44Z0cPzcllw5AfbQhSNhFo0bgK28qa3WHctb33qkfvr+TUiah8EgrOoASiBaN5QlMjPoFAgMBAAECggEBAJ8OpFTLnpCoArZNOrGbDo7ga7dQa2YdXb2QBKhwf+qlbYUNRCp+S3sytp27eDcPX8++x0FD3S6JKAHRpybziCFvSyjH1jQr1+gpbsLE5LeeaG0OPqRzNcWLdCRuM/vyhL2SF9d16fW5Nu+kQZp0+16xja53JO1ZxUKrjED2hLDq4njJThj5NhfSFYiwWkFDxbUHMdKBKxnBSv9woYmRbjSAG4XTSyEyDJZxOI6+uP2z0LRIMo7EXNG4oAnUB6gKzk2QAoh6uZ8qQiDzp2QR/b5pjnGVbGKPyROJ181xgTVHpxwHg5kEcg1vzV94vCbOgyK4Jjie5Nvq+wAj+Lwf79ECgYEA2Mu+iYYdjRUiFj0g5iwjvDGf+ykV0OvV5aww3MM58Ushl8HaI5Cx9pDoLk4TqM51nfRoHRjpOc1O4G1Ab/OtHKmw/9TTuk0adwxOhhHa872EnivnJUuLbORkQy78aPDYXY9gfSN5Qda2jm6S/9ljhr7Qy9fzKmk+zLiiIlmvxgMCgYEAu+0g60+NzWgnwOlXxn9C51vYlWHnMfWJpq6xyhsI+a4q33V4Stgy9fuop82q22T84QcuDJ3T3c9kMt15v8/N4rPSgErzdBH9S2/+UlLVcKJYzl4OKD3cXISPvdEXxylWXa4PBVVDeHM9yu6uSnXmZMmUm9rxTBUgnTHc87yQ5VcCgYEArMlONft/R/eE8GaN9wVNAewnTp1DNv0PwHqJgxnR739q6bHl8xyayiJZ+rXds1fBpD+sTyyQjbIVYIeSqOd0nHieingMu9pB8ooLd415jXQJ46qoEjBwkjxBsM4c/GKgf4kLZRFFznSbkU+Nt36CdV1VunGF69Jv6+hnyKrkjhMCgYEAgTwQcGI0JTbkZ8DQcRwhWVOLPSenw26UrADlGRTAdlv09dkl0WqemRTopgVE4z2uvd2fKGP9SEOQJ36xdFwmvSlmsH1asM6SPyeUYO7AA4ZRxhFecQQhn6o145aAYKEn2KKTh9pb3OB7dSvwxhoQR+1sekX1vJmm5RNeXGunxV8CgYAP9+8jXEG+B6Xb6Axih1Ws/D8Lino/A9wZV8/XWhdlWzvsGQVt+6n7zYDdDOO2OUFHDItlJl4z+/XxqIW6i/gyCt+33bGjR+SUsvTpI+lrMnHQrDHkA/FgBCYzmtEoJlyRFD9Ofshn1SR9kXQRcu5y1KFm9Hh8qMg3DefPnzaM2w==";
	private String CHARSET="UTF-8";
	private String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkweKple0nEsnkPWP8WuR55breUr4atYHemQpL6OnaPcwGJT0SeMRiZWkJ1tlg81h7vl8j3wJ/eOD16Dr3JOufWEExEaC7MEgFR8OMs8BU3vnq8iCDQ+u1aZZsfJg5GhSVyDk+uuVsn0D8DTvh9emhrMxuGX7AMgNis/aFQl+weUAy7GUbiAaVh628PcxV9X640EhMWZkvQvPKKLK7el0AWH3xgu+ckN4J7XUeypuImlB+pkfHHAUuHfcb4jlqnqSWhjCmbWJnCaPLX+7FoKxyYM4/YbtvQJwah9BDTmORueuCIxF4sf6W5UlAYwLFBBr8c5b7EnG49cRiC7JZvEJtQIDAQAB";
	
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String gradestring=request.getParameter("grade");  
		String classnumberstring=request.getParameter("classnumber");
		String phonestring=request.getParameter("phonenumber");
		String ordertable=request.getParameter("ordertable");
		
		
		Double price=0d;
		String out_trade_no="";
		try {
			FindClassDetailDao findClassDetailDao=new FindClassDetailDao();
			price = findClassDetailDao.FindClassPriceAction(gradestring, classnumberstring);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			ALiPay aLiPay=new ALiPay();
			out_trade_no=""+aLiPay.insertOrder(classnumberstring, phonestring, ordertable, price, 0d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		

		
		

	}
}
