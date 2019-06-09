package ml.weiyan.qa;

import ml.weiyan.utils.IdWorker;
import ml.weiyan.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableEurekaClient    //注册到eureka注册中心
@EnableDiscoveryClient //获取注册中心的服务
@EnableFeignClients    //通过Feign的方法去调用
public class ApplicationQa {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationQa.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}
