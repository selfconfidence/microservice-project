package ml.weiyan.sendmessage;

import ml.weiyan.utils.SendMessages6Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package ml.weiyan.sendmessage
 * @date 2019/4/14 16:42
 */
@SpringBootApplication
public class ApplicationSendMessage {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSendMessage.class,args);
    }
    @Bean
    public SendMessages6Util getCode(){
        return new SendMessages6Util();
    }
}
