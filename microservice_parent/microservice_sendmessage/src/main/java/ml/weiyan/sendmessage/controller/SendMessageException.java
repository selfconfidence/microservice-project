package ml.weiyan.sendmessage.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author misterWei
 * @create 2019年04月13号:19点13分
 * @mailbox mynameisweiyan@gmail.com
 * 配置全局异常类
 */
@RestControllerAdvice
public class SendMessageException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity error(Exception e){
        return new ResponseEntity(false, ResponseCode.ERROR, e.getMessage());
    }
}
