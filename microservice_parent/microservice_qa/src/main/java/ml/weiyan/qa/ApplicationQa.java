package ml.weiyan.qa;

import ml.weiyan.utils.IdWorker;
import ml.weiyan.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
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
