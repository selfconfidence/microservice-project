package ml.weiyan.spit.service;

import ml.weiyan.spit.dao.SpitDao;
import ml.weiyan.spit.pojo.Spit;
import ml.weiyan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

   public List<Spit> findAll(){
       return spitDao.findAll();
   }
   public void save(Spit spit){
       spit.set_id(idWorker.nextId());
       spitDao.save(spit);
   }
}
