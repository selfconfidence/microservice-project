package ml.weiyan.user.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.result.ResponsePageEntity;
import ml.weiyan.user.pojo.Admin;
import ml.weiyan.user.service.AdminService;
import ml.weiyan.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return new ResponseEntity(true, ResponseCode.OK, "查询成功", adminService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable String id) {
        return new ResponseEntity(true, ResponseCode.OK, "查询成功", adminService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResponseEntity findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
        return new ResponseEntity(true, ResponseCode.OK, "查询成功", new ResponsePageEntity<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity findSearch(@RequestBody Map searchMap) {
        return new ResponseEntity(true, ResponseCode.OK, "查询成功", adminService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param admin
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Admin admin) {
        if (admin.getLoginname() == null || admin.getPassword() == null) {
            return new ResponseEntity(false, ResponseCode.ERROR, "内容请补全");
        }
        try {
            adminService.add(admin);
        } catch (Exception e) {
            return new ResponseEntity(false, ResponseCode.ERROR, e.getMessage());
        }

        return new ResponseEntity(true, ResponseCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param admin
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return new ResponseEntity(true, ResponseCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        adminService.deleteById(id);
        return new ResponseEntity(true, ResponseCode.OK, "删除成功");
    }

    /**
     * 登录
     * @param admin
     * @return
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Admin admin) {
        if (admin.getLoginname() == null || admin.getPassword() == null) {
            return new ResponseEntity(false, ResponseCode.ERROR, "内容请补全");
        }

       Admin adminEntity =  adminService.login(admin);

        if (adminEntity == null){
            return new ResponseEntity(false, ResponseCode.LOGINERROR, "验证未通过");

        }
        //使用JWT进行用户存储操作。
        String token = jwtUtil.createJWT(adminEntity.getId(), adminEntity.getLoginname(), "admin");

        return new ResponseEntity(true, ResponseCode.OK, "验证通过",token);


    }

}
