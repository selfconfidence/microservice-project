package ml.weiyan.qa.exception;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author misterWei
 * @create 2019年05月25号:18点05分
 * @mailbox mynameisweiyan@gmail.com
 */
@RestControllerAdvice
public class OverallException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity overallException(Exception e){
        return new ResponseEntity(false, ResponseCode.ERROR,e.getMessage());
    }
}
