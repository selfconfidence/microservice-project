package ml.weiyan.user;

import ml.weiyan.utils.IdWorker;
import ml.weiyan.utils.PhoneFormatCheckUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class ApplicationUser {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationUser.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
    public PhoneFormatCheckUtils phoneFormatCheckUtils(){
	    return new PhoneFormatCheckUtils();
    }
}
