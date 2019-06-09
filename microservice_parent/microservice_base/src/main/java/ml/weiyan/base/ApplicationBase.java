package ml.weiyan.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author misterWei
 * @create 2019年03月24号:19点26分
 * @mailbox mynameisweiyan@gmail.com
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationBase {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBase.class,args);
    }
}
