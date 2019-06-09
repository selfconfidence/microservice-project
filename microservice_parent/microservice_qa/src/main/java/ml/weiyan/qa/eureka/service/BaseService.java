package ml.weiyan.qa.eureka.service;

import ml.weiyan.result.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author misterWei
 * @create 2019年06月09号:13点27分
 * @mailbox mynameisweiyan@gmail.com
 * 声明一个接口调用类
 */
@Component //这个注解写不写都是无所谓的.
@FeignClient("microservice-base") //使用名称调用,该名称就是每个服务yml配置中的application.name
public interface BaseService {
    //该路径如果调用远程的controller,就需要全路径匹配注解类上的RequestMapping + 方法上的RequestMapping的路径
    @RequestMapping(value ="/label/label/{labelId}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("labelId") String labelId);
}
