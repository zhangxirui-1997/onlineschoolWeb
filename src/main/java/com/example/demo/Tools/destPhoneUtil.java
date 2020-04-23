package com.example.demo.Tools;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Classname destPhone
 * @Date 2020/4/23
 */

//手机验证码
public class destPhoneUtil {


    public static String phone(String phoneNumber){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FzF3L4fBPkqePpwAMBX", "75KxaPz0ZgFtZninMeJVaLaeBYMdnG");
        IAcsClient client = new DefaultAcsClient(profile);

        String code=newcode();

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "百学教育");
        request.putQueryParameter("TemplateCode", "SMS_188641640");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //验证码返回给前端
        return code;
    }
    //生成6位随机验证码
    public static String newcode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        System.out.println(vcode);
        return vcode;
    }
}
