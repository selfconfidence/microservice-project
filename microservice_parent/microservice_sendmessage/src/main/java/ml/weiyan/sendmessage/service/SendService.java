package ml.weiyan.sendmessage.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import ml.weiyan.sendmessage.config.SendConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package ml.weiyan.sendmessage.service
 * @date 2019/4/14 16:43
 */
@Service
public class SendService {

    @Autowired
    private SendConfig snedConfig;

    public Map sendMessage(String code, String phone) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("default", snedConfig.getAccessKey(), snedConfig.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = null;
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "Microservice");
        request.putQueryParameter("TemplateCode", "SMS_163432797");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        response = client.getCommonResponse(request);
        return JSON.parseObject(response.getData(), Map.class);
    }
}
