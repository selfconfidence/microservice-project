package com.weiyan.ml;

import com.ml.weiyan.RabbitMqApplcation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author misterWei
 * @create 2019年05月02号:13点42分
 * @mailbox mynameisweiyan@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqApplcation.class)
public class ProviderTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test //点对点模式  直接模式 操作queue
    public void sendMes(){
       rabbitTemplate.convertAndSend("list","ceshi");
    }

    @Test //队列模式  分列模式 操作queue  使用交换器
    public void sendMes2(){
        rabbitTemplate.convertAndSend("a","","ceshi");
    }
    @Test// 主题模式  使用交换器操作Routing key 进行适配 操作
    public void sendMes3(){
        rabbitTemplate.convertAndSend("b","best.e","ceshi");

    }

}
