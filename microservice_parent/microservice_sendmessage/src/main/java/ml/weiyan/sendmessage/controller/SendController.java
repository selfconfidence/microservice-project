package ml.weiyan.sendmessage.controller;

import com.aliyuncs.exceptions.ClientException;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.sendmessage.service.SendService;
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

    @RequestMapping(value = "/message/{phone}",method = RequestMethod.GET)
    public ResponseEntity sendMessage(@PathVariable("phone") String phone) throws ClientException {
        Map result = sendService.sendMessage(sendMessages6Util.smsMessageRandom(), phone);
      return new ResponseEntity(true, ResponseCode.OK,"发送成功!",result.get("code"));
    }
}
