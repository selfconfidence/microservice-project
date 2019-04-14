package ml.weiyan.sendmessage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package ml.weiyan.sendmessage.config
 * @date 2019/4/14 17:13
 */
@Component
@ConfigurationProperties(prefix = "ml.aliyun.message")
@PropertySource("classpath:/config/token.properties")
public class SendConfig {

    private String accessKey;
    private String accessKeySecret;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
