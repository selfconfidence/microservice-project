package ml.weiyan.base.dao;

import ml.weiyan.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author misterWei
 * @create 2019年03月24号:19点33分
 * @mailbox mynameisweiyan@gmail.com
 */
@Repository
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {



}
