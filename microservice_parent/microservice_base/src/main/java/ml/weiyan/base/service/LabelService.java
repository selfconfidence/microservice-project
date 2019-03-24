package ml.weiyan.base.service;

import ml.weiyan.base.dao.LabelDao;
import ml.weiyan.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author misterWei
 * @create 2019年03月24号:19点33分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public void remove(String id){
        labelDao.deleteById(id);
    }

    public void saveOrUpdate(Label label){
        if (labelDao.existsById(label.getId())){
            labelDao.saveAndFlush(label);
        }else {
            labelDao.save(label);
        }
        labelDao.saveAndFlush(label);
    }
    public Label findOne(String id){
        Label labelDaoOne = labelDao.getOne(id);
        return labelDaoOne;
    }
}
