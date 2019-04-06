package ml.weiyan.article.controller;

import ml.weiyan.article.pojo.Channel;
import ml.weiyan.article.service.ChannelService;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.result.ResponsePageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity findAll(){
		return new ResponseEntity(true, ResponseCode.OK,"查询成功",channelService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResponseEntity findById(@PathVariable String id){
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",channelService.findById(id));
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
		Page<Channel> pageList = channelService.findSearch(searchMap, page, size);
		return  new ResponseEntity(true,ResponseCode.OK,"查询成功",  new ResponsePageEntity<>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResponseEntity findSearch( @RequestBody Map searchMap){
        return new ResponseEntity(true,ResponseCode.OK,"查询成功",channelService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param channel
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody Channel channel  ){
		channelService.add(channel);
		return new ResponseEntity(true,ResponseCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param channel
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResponseEntity update(@RequestBody Channel channel, @PathVariable String id ){
		channel.setId(id);
		channelService.update(channel);		
		return new ResponseEntity(true,ResponseCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String id ){
		channelService.deleteById(id);
		return new ResponseEntity(true,ResponseCode.OK,"删除成功");
	}
	
}
