package com.example.demo.Servlet;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import jdk.nashorn.internal.runtime.URIUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import static com.example.demo.Tools.videoUtil.GetClient.initVodClient;
import static com.example.demo.Tools.videoUtil.GetPlayInfo.getPlayInfo;

/**
 * @Classname GetVideoServlet
 * @Date 2020/4/26
 * @Created by 陈刀仔
 * @Description TODO
 */

@WebServlet("/GetVideoServlet")
public class GetVideoServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String name=req.getParameter("name");
        System.out.println();
        System.out.println("得到id了吗："+id);
        //播放地址
        String path="";
        DefaultAcsClient client = null;
        try {
            client = initVodClient("LTAI4Fu8YH8LtmbmrvXdcbtF", "BHohBxrBQ6q9GntmMOECF4hHeB3GO2");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            try {
                response = getPlayInfo(client, id);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                 path=playInfo.getPlayURL();
            }
            //视频标题
            String title = response.getVideoBase().getTitle();
            name=title;
            System.out.print("得到路径了吗" + path + "\n");
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
//        //跳转到播放页面
//        req.setAttribute("path",path);
//        req.getRequestDispatcher("/video/123456.html").forward(req, resp);

        System.out.println("123456.html应该是这个name"+name);
//        resp.sendRedirect("/video/123456.html?showname="+showname+"&&path="+path);
        resp.sendRedirect("/video/123456.html?name="+ URLEncoder.encode(name,"utf-8")+"&&path="+path);
    }
}