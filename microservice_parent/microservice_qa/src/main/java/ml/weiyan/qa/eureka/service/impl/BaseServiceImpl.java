package ml.weiyan.qa.eureka.service.impl;

/**
 * @author misterWei
 * @create 2019年06月15号:20点09分
 * @mailbox mynameisweiyan@gmail.com
 */

import ml.weiyan.qa.eureka.service.BaseService;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 熔断器的实现
 */
@Component
public class BaseServiceImpl implements BaseService {
    @Override
    public ResponseEntity findOne(String labelId) {
        /**
         * 在这里可以打印日志,通知人员,远程服务已被停用.
         */
        return new ResponseEntity(true, ResponseCode.ACCESSERROR,"熔断器启动!");
    }
}
