package ml.weiyan.qa.config;
import ml.weiyan.qa.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * @author misterWei
 * @create 2019年05月25号:17点37分
 * @mailbox mynameisweiyan@gmail.com
 * //这个配置主要是用来让自定义的拦截器生效
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加拦截请求路径              //添加不被拦截的请求路径
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**");
    }
}
