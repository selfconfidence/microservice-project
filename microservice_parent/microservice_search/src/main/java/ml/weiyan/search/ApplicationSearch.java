package ml.weiyan.search;

import ml.weiyan.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author misterWei
 * @create 2019年04月13号:18点17分
 * @mailbox mynameisweiyan@gmail.com
 */
@SpringBootApplication
public class ApplicationSearch {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSearch.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

}
