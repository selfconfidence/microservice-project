package ml.weiyan.user.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.result.ResponsePageEntity;
import ml.weiyan.user.pojo.User;
import ml.weiyan.user.service.UserService;
import ml.weiyan.utils.PhoneFormatCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PhoneFormatCheckUtils phoneFormatCheckUtils;

	@Autowired
	private RedisTemplate redisTemplate;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity findAll(){
		return new ResponseEntity(true, ResponseCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResponseEntity findById(@PathVariable String id){
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public ResponseEntity findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new ResponseEntity(true,ResponseCode.OK,"查询成功",  new ResponsePageEntity<>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResponseEntity findSearch( @RequestBody Map searchMap){
        return new ResponseEntity(true,ResponseCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody User user  ){
		userService.add(user);
		return new ResponseEntity(true,ResponseCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResponseEntity update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new ResponseEntity(true,ResponseCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String id ){
		userService.deleteById(id);
		return new ResponseEntity(true,ResponseCode.OK,"删除成功");
	}

	/**
	 * 发送验证码信息
	 * @param mobile
	 * @return
	 */

	@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
	public ResponseEntity sendsms(@PathVariable("mobile") String mobile){
		if (!phoneFormatCheckUtils.isPhoneLegal(mobile)) {
			return new ResponseEntity(false,ResponseCode.ERROR,"手机号码格式错误!");
		}
		userService.sendSms(mobile);
		return new ResponseEntity(true,ResponseCode.OK,"发送成功!");
	}

	@RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
	public ResponseEntity register(@PathVariable("code")String code,@RequestBody User user){
		//业务逻辑验证码进行匹配。是否已经发送过验证码！
		if (code == null) {
			return new ResponseEntity(true,ResponseCode.ERROR,"验证码为空！");
		}
	    //取出redis的缓存数据进行比对
        String checkCode = (String) redisTemplate.opsForValue().get("user_sms"+user.getMobile());
		if (!code.equals(checkCode)) {
			return new ResponseEntity(true,ResponseCode.ERROR,"验证码过时！");
		}
		userService.register(user);

		return new ResponseEntity(true,ResponseCode.OK,"注册成功!");

	}
}
