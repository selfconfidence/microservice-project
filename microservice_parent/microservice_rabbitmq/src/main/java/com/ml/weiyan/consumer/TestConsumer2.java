package com.ml.weiyan.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author misterWei
 * @create 2019年05月02号:13点39分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
@RabbitListener(queues = "list2")
public class TestConsumer2 {

    @RabbitHandler
    public void getMes(String ceshi){
        System.err.println("模式:=="+ ceshi);
    }
}
