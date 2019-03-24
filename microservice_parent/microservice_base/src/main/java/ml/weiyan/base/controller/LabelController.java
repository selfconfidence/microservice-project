package ml.weiyan.base.controller;

import ml.weiyan.base.pojo.Label;
import ml.weiyan.base.service.LabelService;
import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author misterWei
 * @create 2019年03月24号:19点33分
 * @mailbox mynameisweiyan@gmail.com
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/label",method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return new ResponseEntity(true, ResponseCode.OK,"查询成功！",labelService.findAll());
    }

    @RequestMapping(value =  "/label",method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Label label){
             label.setId(idWorker.nextId());
             labelService.saveOrUpdate(label);
        return  new ResponseEntity(true, ResponseCode.OK,"保存成功！");
    }
    @RequestMapping(value =  "/label/{labelId}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("labelId") String labelId){
        Label label = labelService.findOne(labelId);
        return  new ResponseEntity(true, ResponseCode.OK,"查询成功！",label);
    }
    @RequestMapping(value =  "/label/{labelId}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("labelId") String labelId,@RequestBody Label label){
        labelService.saveOrUpdate(label);
        return  new ResponseEntity(true, ResponseCode.OK,"修改成功！");
    }

    @RequestMapping(value =  "/label/{labelId}",method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("labelId") String labelId){
       labelService.remove(labelId);
        return  new ResponseEntity(true, ResponseCode.OK,"删除成功！");
    }
}
