package ml.weiyan.sendmessage.listen;

import com.aliyuncs.exceptions.ClientException;
import ml.weiyan.sendmessage.service.SendService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author misterWei
 * @create 2019年05月18号:17点21分
 * @mailbox mynameisweiyan@gmail.com
 */
//监听消息
@Component
@RabbitListener(queues = "sendMesUser")
public class UserSmsSend {

    @Autowired
    private SendService sendService;

    @RabbitHandler
    public void smsSend(Map mobileCode) throws ClientException {
        String mobile = (String) mobileCode.get("mobile");
        String checkCode = (String) mobileCode.get("checkCode");
        sendService.sendMessage(checkCode,mobile);
    }
}
