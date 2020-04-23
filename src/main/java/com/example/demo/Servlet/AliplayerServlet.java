package com.example.demo.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.Tools.ParamsComparator;
import sun.misc.BASE64Encoder;


/**
 * Servlet implementation class AliplayerServlet
 */
@WebServlet("/AliplayerServlet")
public class AliplayerServlet extends HttpServlet {

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-UTF-8");

		String VideoId=request.getParameter("VideoId");

		Map<String, String> publicParams=new HashMap<String,String>();
		Map<String, String> privateParams=new HashMap<String, String>();

		String ApiRegion_string="http://vod.cn-shanghai.aliyuncs.com/";
		String Forma_string="JSON";
		String Version_string="2017-03-21";
		String AccessKeyId_string="LTAI4Fu8YH8LtmbmrvXdcbtF";
		String Signature_string="";
		String SignatureMethod_string="HMAC-SHA1";
		String Timestamp_string=generateTimestamp();
		String SignatureVersion_string="1.0";
		String SignatureNonce_string=generateRandom();

		String Action_string="GetVideoPlayAuth";
		String VideoId_string=VideoId;

		String AccessKeySecret="BHohBxrBQ6q9GntmMOECF4hHeB3GO2";

		publicParams.put("Format", Forma_string);
		publicParams.put("Version",Version_string );
		publicParams.put("AccessKeyId", AccessKeyId_string);
		publicParams.put("SignatureMethod", SignatureMethod_string);
		publicParams.put("Timestamp", Timestamp_string);
		publicParams.put("SignatureVersion", SignatureVersion_string);
		publicParams.put("SignatureNonce", SignatureNonce_string);

		privateParams.put("Action", Action_string);
		privateParams.put("VideoId", VideoId_string);

		List<String> list=getAllParams(publicParams, privateParams);
		String cqString=getCQS(list);
		System.out.println("cqs："+cqString);
		String stringtosign=
				"GET" + "&" + //HTTPMethod：发送请求的 HTTP 方法，例如 GET。
						percentEncode("/") + "&" + //percentEncode("/")：字符（/）UTF-8 编码得到的值，即 %2F。
						percentEncode(cqString); //您的规范化请求字符串。
		System.out.println("stringtosign："+stringtosign);
		byte[] bs=hmacSHA1Signature(AccessKeySecret, stringtosign);
		Signature_string=newStringByBase64(bs);
		System.out.println("Signature_string："+Signature_string);
		publicParams.put("Signature",Signature_string);
		String URL_API="";
		URL_API=getUrl(ApiRegion_string, publicParams, privateParams);
		System.out.println("生成的最终访问地址"+URL_API);

		String result=sendGet(URL_API);

		PrintWriter outPrintWriter=response.getWriter();
		outPrintWriter.println(result);
		System.out.println(result);
		outPrintWriter.close();
	}

	//发送GET请求
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(4000);
			conn.connect();// 建立实际的连接
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭流异常");
			}
		}
		return result;
	}

	public static String getUrl(String ApiRegion_string, Map<String, String> publicParams, Map<String, String> privateParams) {
		String thisUrl="";
		thisUrl=ApiRegion_string+"?";
		if (privateParams != null) {
			for (String key : privateParams.keySet()) {
				String value = privateParams.get(key);
				//将参数和值都urlEncode一下。
				String encodeKey = percentEncode(key);
				String encodeVal = percentEncode(value);
				String nowstring=encodeKey+"="+encodeVal+"&";
				thisUrl+=nowstring;
			}
		}
		if (publicParams != null) {
			for (String key : publicParams.keySet()) {
				String value = publicParams.get(key);
				//将参数和值都urlEncode一下。
				String encodeKey = percentEncode(key);
				String encodeVal = percentEncode(value);
				String nowstring=encodeKey+"="+encodeVal+"&";
				thisUrl+=nowstring;

			}
		}
		return thisUrl;
	}

	public static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
		List<String> encodeParams = new ArrayList<String>();
		if (publicParams != null) {
			for (String key : publicParams.keySet()) {
				String value = publicParams.get(key);
				//将参数和值都urlEncode一下。
				String encodeKey = percentEncode(key);
				String encodeVal = percentEncode(value);
				encodeParams.add(encodeKey + "=" + encodeVal);
			}
		}
		if (privateParams != null) {
			for (String key : privateParams.keySet()) {
				String value = privateParams.get(key);
				//将参数和值都urlEncode一下。
				String encodeKey = percentEncode(key);
				String encodeVal = percentEncode(value);
				encodeParams.add(encodeKey + "=" + encodeVal);
			}
		}
		return encodeParams;
	}

	public static String percentEncode(String value) {
		try {
			String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
			String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
			String starReplaced = plusReplaced.replace("*", "%2A");
			String waveReplaced = starReplaced.replace("%7E", "~");
			return waveReplaced;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/*获取 CanonicalizedQueryString*/
	public static String getCQS(List<String> allParams) {
		ParamsComparator paramsComparator = new ParamsComparator();
		Collections.sort(allParams, paramsComparator);
		String cqString = "";
		for (int i = 0; i < allParams.size(); i++) {
			cqString += allParams.get(i);
			if (i != allParams.size() - 1) {
				cqString += "&";
			}
		}
		return cqString;
	}

	public static byte[] hmacSHA1Signature(String accessKeySecret, String stringToSign) {
		try {
			String key = accessKeySecret + "&";
			try {
				SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
				Mac mac = Mac.getInstance("HmacSHA1");
				mac.init(signKey);
				return mac.doFinal(stringToSign.getBytes());
			} catch (Exception e) {
				throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
			}
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String newStringByBase64(byte[] bytes)
			throws UnsupportedEncodingException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return new String(new BASE64Encoder().encode(bytes));
	}




	/*生成当前UTC时间戳Time*/
	public static String generateTimestamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

	public static String generateRandom() {
		String signatureNonce1 = UUID.randomUUID().toString();
		return signatureNonce1;
	}
}










