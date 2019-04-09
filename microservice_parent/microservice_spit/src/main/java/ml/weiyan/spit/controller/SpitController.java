package ml.weiyan.spit.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.spit.pojo.Spit;
import ml.weiyan.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return new ResponseEntity(true, ResponseCode.OK,"查询成功!",spitService.findAll());
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Spit spit){
        spitService.save(spit);
        return new  ResponseEntity(true, ResponseCode.OK,"添加成功!");
    }
}
