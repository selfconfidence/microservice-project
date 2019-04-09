package ml.weiyan.spit;

import ml.weiyan.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author misterWei
 * @create 2019年04月07号:21点07分
 * @mailbox mynameisweiyan@gmail.com
 */
@SpringBootApplication
public class ApplicationSpit {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpit.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
