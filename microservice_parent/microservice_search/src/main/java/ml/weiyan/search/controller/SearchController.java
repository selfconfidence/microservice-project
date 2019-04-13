package ml.weiyan.search.controller;

import ml.weiyan.result.ResponseCode;
import ml.weiyan.result.ResponseEntity;
import ml.weiyan.result.ResponsePageEntity;
import ml.weiyan.search.pojo.ArticleSearch;
import ml.weiyan.search.service.SearchService;
import ml.weiyan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author misterWei
 * @create 2019年04月13号:22点04分
 * @mailbox mynameisweiyan@gmail.com
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ArticleSearch articleSearch){
        articleSearch.setId(idWorker.nextId());
        searchService.save(articleSearch);
        return new ResponseEntity(true, ResponseCode.OK,"添加成功!");
    }
    /**
     * 根据标题词条搜索并分页查询
     */

    @RequestMapping(value = "/search/{key}/{page}/{size}",method = RequestMethod.GET)
    public ResponseEntity findByKeyWord(@PathVariable("key") String key,@PathVariable("page") int page,@PathVariable("size") int size){

       Page pageData = searchService.findByKeyWord(key,page,size);
    return new ResponseEntity(true,ResponseCode.OK,"查询成功!",new ResponsePageEntity<>(pageData.getTotalElements(),pageData.getContent()));
    }
}
