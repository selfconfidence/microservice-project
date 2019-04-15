package ml.weiyan.sendmessage.controller;

import com.aliyuncs.exceptions.ClientException;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.sendmessage.service.SendService;
import ml.weiyan.utils.PhoneFormatCheckUtils;
import ml.weiyan.utils.SendMessages6Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package ml.weiyan.sendmessage.controller
 * @date 2019/4/14 16:42
 */
@RestController
@CrossOrigin
@RequestMapping("/send")
public class SendController {
    @Autowired
    private SendService sendService;

    @Autowired
    private SendMessages6Util sendMessages6Util;

    @Autowired
    private PhoneFormatCheckUtils phoneFormatCheckUtils;

    @RequestMapping(value = "/message/{phone}",method = RequestMethod.GET)
    public ResponseEntity sendMessage(@PathVariable("phone") String phone) throws ClientException {
        //校验手机号码是否正确
        if (!phoneFormatCheckUtils.isPhoneLegal(phone)) {
            throw new RuntimeException("手机号码格式有误!");
        }
        Map result = sendService.sendMessage(sendMessages6Util.smsMessageRandom(), phone);

      return new ResponseEntity(true, ResponseCode.OK,"发送成功!",result.get("code"));
    }
}
