package ml.weiyan.user.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author misterWei
 * @create 2019年05月18号:17点25分
 * @mailbox mynameisweiyan@gmail.com
 */
@RestControllerAdvice
public class ExceptionIntercept {
    @ExceptionHandler(Exception.class)
    public ResponseEntity error(Exception e) {
        return new ResponseEntity(false, ResponseCode.ERROR, e.getMessage());
    }

}
