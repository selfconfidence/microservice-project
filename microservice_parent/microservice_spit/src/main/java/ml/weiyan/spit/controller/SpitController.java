package ml.weiyan.spit.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.result.ResponsePageEntity;
import ml.weiyan.spit.pojo.Spit;
import ml.weiyan.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author misterWei
 * @create 2019年04月07号:21点13分
 * @mailbox mynameisweiyan@gmail.com
 */
@RequestMapping("/spit")
@CrossOrigin
@RestController
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return new ResponseEntity(true, ResponseCode.OK,"查询成功!",spitService.findAll());
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Spit spit){
        spitService.save(spit);
        return new  ResponseEntity(true, ResponseCode.OK,"添加成功!");
    }
    @RequestMapping(value = "/{_id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("_id") String _id){
        return new ResponseEntity(true, ResponseCode.OK,"查询成功!",spitService.findOne(_id));
    }

    @RequestMapping(value = "/{_id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("_id") String _id){
        spitService.deleteById(_id);
        return new ResponseEntity(true, ResponseCode.OK,"删除成功!");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public ResponseEntity findPage(@PathVariable("parentid") String parentid,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Spit> parentAndPage = spitService.findParentAndPage(parentid, page, size);
        return new ResponseEntity(true,ResponseCode.OK,"查询成功!",new ResponsePageEntity<>(parentAndPage.getTotalElements(),parentAndPage.getContent()));
    }
    @RequestMapping(value = "/thumbup/{spitid}",method = RequestMethod.PUT)
   public ResponseEntity thumbup(@PathVariable("spitid") String spitid){
        //控制点赞数目,同一个用户不能对多个评论点赞.使用redis
         String userId = "123456";
        if (redisTemplate.opsForValue().get("thumbup_"+userId+spitid) != null) {
            return new ResponseEntity(false,ResponseCode.REMOTEERROR,"不可点赞!");
        }
        spitService.thumbup(spitid);
        redisTemplate.opsForValue().set("thumbup_"+userId+spitid,spitid);
        return new ResponseEntity(true,ResponseCode.OK,"点赞成功!");

    }
}
