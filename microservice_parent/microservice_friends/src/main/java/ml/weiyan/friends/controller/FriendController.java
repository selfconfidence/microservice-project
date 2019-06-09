package ml.weiyan.friends.controller;

import io.jsonwebtoken.Claims;
import ml.weiyan.friends.eureka.service.UserService;
import ml.weiyan.friends.service.FriendService;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author misterWei
 * @create 2019年06月09号:18点12分
 * @mailbox mynameisweiyan@gmail.com
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public ResponseEntity likeFriend(@PathVariable("friendid") String friendid, @PathVariable("type") String type) {
        //获取登陆信息,查询用户信息
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new ResponseEntity(false, ResponseCode.ERROR, "权限不足");
        }
        String userId = claims.getId();
        if (type != null) {
            if ("1".equals(type)) {
                //喜欢
                //判断是否是重复添加操作
                int flag = friendService.addFriendId(userId, friendid);
                if (flag == 0) {
                    return new ResponseEntity(false, ResponseCode.ERROR, "不可重复喜欢~");
                }
                //同时需要为当前用户添加关注量,为好友添加粉丝数
                userService.updateFanscountAndFollowcount(userId,friendid,1);
                return new ResponseEntity(true, ResponseCode.OK, "添加到喜欢~");
            } else if ("2".equals(type)) {
                // 不喜欢
                int flag = friendService.addNoFriendId(userId, friendid);
                //判断是否是重复添加操作
                if (flag == 0) {
                    return new ResponseEntity(false, ResponseCode.ERROR, "不可重复喜欢~");
                }
                return new ResponseEntity(false, ResponseCode.ERROR, "添加到不喜欢~");

            } else {
                return new ResponseEntity(false, ResponseCode.ERROR, "参数错误~");
            }

        } else {
            return new ResponseEntity(false, ResponseCode.ERROR, "参数错误~");

        }

    }
    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public ResponseEntity deleteFriend(@PathVariable("friendid")String friendid){
        //获取登陆信息,查询用户信息
        Claims claims = (Claims) request.getAttribute("claims_admin");
        if (claims == null) {
            return new ResponseEntity(false, ResponseCode.ERROR, "权限不足");
        }
        String userId = claims.getId();
        //删除当前好友数据,并把好友那条islike变成0 , 调用远程服务关注,粉丝 - 1添加不喜欢好友数据
        friendService.deleteFriend(userId,friendid);
        userService.updateFanscountAndFollowcount(userId,friendid,-1);
        return new ResponseEntity(true, ResponseCode.OK, "解除关系成功~");

    }
}
