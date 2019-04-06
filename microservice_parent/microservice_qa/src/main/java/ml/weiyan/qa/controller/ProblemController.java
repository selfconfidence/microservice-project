package ml.weiyan.qa.controller;

import ml.weiyan.qa.pojo.Problem;
import ml.weiyan.qa.service.ProblemService;
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
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity findAll(){
		return new ResponseEntity(true, ResponseCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResponseEntity findById(@PathVariable String id){
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",problemService.findById(id));
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
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new ResponseEntity(true,ResponseCode.OK,"查询成功",  new ResponsePageEntity<>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResponseEntity findSearch( @RequestBody Map searchMap){
        return new ResponseEntity(true,ResponseCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return new ResponseEntity(true,ResponseCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResponseEntity update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new ResponseEntity(true,ResponseCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new ResponseEntity(true,ResponseCode.OK,"删除成功");
	}
	/**
	 * 最新问答回复列表
	 * @return
	 */
	@RequestMapping(value = "/newlist/{label}/{page}/{size}",method = RequestMethod.GET)
	 public ResponseEntity newList(@PathVariable("label") String lable,@PathVariable("page") int page,@PathVariable("size") int size){
		Page<Problem> problems = this.problemService.newList(lable, page, size);
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",new ResponsePageEntity<>(problems.getTotalElements(),problems.getContent()));

	}
	/**
	 * 热门问答列表
	 * @return
	 */
	@RequestMapping(value = "/hostlist/{label}/{page}/{size}",method = RequestMethod.GET)
	public ResponseEntity hostList(@PathVariable("label") String lable,@PathVariable("page") int page,@PathVariable("size") int size){
		Page<Problem> problems = this.problemService.hotList(lable, page, size);
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",new ResponsePageEntity<>(problems.getTotalElements(),problems.getContent()));

	}
	/**
	 * 等待回答列表
	 * @return
	 */
	@RequestMapping(value = "/waitlist/{label}/{page}/{size}",method = RequestMethod.GET)
	public ResponseEntity waitList(@PathVariable("label") String lable,@PathVariable("page") int page,@PathVariable("size") int size){
		Page<Problem> problems = this.problemService.waitList(lable, page, size);
		return new ResponseEntity(true,ResponseCode.OK,"查询成功",new ResponsePageEntity<>(problems.getTotalElements(),problems.getContent()));

	}
}
