package ml.weiyan.friends.eureka.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author misterWei
 * @create 2019年06月09号:18点44分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component //这个注解写不写都是无所谓的.
@FeignClient("microservice-user")
public interface UserService {
    @RequestMapping(value = "/user/{userId}/{friendId}/{flag}",method= RequestMethod.PUT)
    public void  updateFanscountAndFollowcount(@PathVariable("userId")String userId, @PathVariable("friendId") String friendId, @PathVariable("flag") int flag);
}
