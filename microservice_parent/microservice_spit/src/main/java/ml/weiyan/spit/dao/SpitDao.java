package ml.weiyan.spit.dao;

import ml.weiyan.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author misterWei
 * @create 2019年04月07号:21点10分
 * @mailbox mynameisweiyan@gmail.com
 */
@Repository
public interface SpitDao extends MongoRepository<Spit,String> {
    public Page<Spit> findByParentid(String parentId, Pageable pageable);
}
