package ml.weiyan.base.configuration;

import ml.weiyan.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author misterWei
 * @create 2019年03月24号:19点38分
 * @mailbox mynameisweiyan@gmail.com
 */
@Configuration
public class BaseConfiguration {

    @Bean
    public IdWorker getIdworker(){
        return new IdWorker();
    }
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
