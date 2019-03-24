package ml.weiyan.base.exception;

/**
 * @author misterWei
 * @create 2019年03月24号:21点05分
 * @mailbox mynameisweiyan@gmail.com
 */

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常类处理
 *
 * @author mister_wei
 */
@RestControllerAdvice
public class BaseException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity error(Exception e) {
        return new ResponseEntity(false, ResponseCode.ERROR, e.getMessage());
    }
}
