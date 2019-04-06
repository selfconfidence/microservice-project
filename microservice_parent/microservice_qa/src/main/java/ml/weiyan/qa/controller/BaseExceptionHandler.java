package ml.weiyan.qa.controller;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity error(Exception e){
        e.printStackTrace();        
        return new ResponseEntity(false, ResponseCode.ERROR, "执行出错");
    }
}
