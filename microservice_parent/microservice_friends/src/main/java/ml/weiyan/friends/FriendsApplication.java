package ml.weiyan.friends;

import ml.weiyan.utils.IdWorker;
import ml.weiyan.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author misterWei
 * @create 2019年06月09号:15点33分
 * @mailbox mynameisweiyan@gmail.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FriendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication.class,args);
    }

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker();
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
