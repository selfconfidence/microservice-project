package ml.weiyan.spit.service;

import ml.weiyan.spit.dao.SpitDao;
import ml.weiyan.spit.pojo.Spit;
import ml.weiyan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author misterWei
 * @create 2019年04月07号:21点12分
 * @mailbox mynameisweiyan@gmail.com
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class SpitService {
   @Autowired
    private SpitDao spitDao;

   @Autowired
    IdWorker idWorker;

   @Autowired
   private MongoTemplate mongoTemplate;

   public List<Spit> findAll(){
       return spitDao.findAll();
   }
   public void save(Spit spit){
       spit.set_id(idWorker.nextId());
       spit.setPublishtime(new Date());
       spit.setComment(0);
       spit.setThumbup(0);
       spit.setVisits(0);
       spit.setComment(0);
       spit.setState("1");
       //查看是否有父节点,有的话需要对父节点进行增加评论数操作
       if (!StringUtils.isEmpty(spit.getParentid())) {
           Update update = new Update();
           Query query = new Query();
           query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
           update.inc("comment",1);
           mongoTemplate.updateFirst(query,update,"spit");
       }
       spitDao.save(spit);
   }

   public Spit findOne(String _id){
       return spitDao.findById(_id).get();
   }

   public void deleteById(String _id){spitDao.deleteById(_id);}

   public Page<Spit> findParentAndPage(String parentId,int page,int size){

       return spitDao.findByParentid(parentId, PageRequest.of(page-1, size));
   }

    public void thumbup(String spitid) {
       //初始化点赞数 使用mongo原生的语法进行初始化点赞数
        Update update = new Update();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        update.inc("thumbup",1);
     mongoTemplate.updateFirst(query,update,"spit");
    }
}
