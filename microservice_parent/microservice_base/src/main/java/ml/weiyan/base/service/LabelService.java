package ml.weiyan.base.service;

import ml.weiyan.base.dao.LabelDao;
import ml.weiyan.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author misterWei
 * @create 2019年03月24号:19点33分
 * @mailbox mynameisweiyan@gmail.com
 */
@SuppressWarnings("all")
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

    public List<Label> search(Label label) {
       return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (label != null){
                    //这是一种添加查询的方式
                    List<Predicate> predicateList = new ArrayList<>();
              if (!StringUtils.isEmpty(label.getId())){
                  Path<String> id = root.get("id");
                  predicateList.add(criteriaBuilder.equal(id,label.getId())) ;
              }
              if (!StringUtils.isEmpty(label.getLabelName())){
                  Path<String> labelName = root.get("labelName");
                  predicateList.add(criteriaBuilder.like(labelName,"%"+label.getLabelName()+"%"));
              }
                    //这是一种添加查询的方式
                    Predicate[] predicates = new Predicate[predicateList.size()];
                    return criteriaBuilder.and(predicateList.toArray(predicates));
                }else {
                    return null;
                }

            }
        });
    }

    /**
     * 根据条件以及分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */

    public Page<Label> searchQuery(Label label, Integer page, Integer size) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root  用来泛形中的属性，生成 列名
             * @param criteriaQuery 用来 排序
             * @param criteriaBuilder 用来连接查询
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (label != null){

                    //这是一种添加查询的方式
                    List<Predicate> predicateList = new ArrayList<>();
                    if (!StringUtils.isEmpty(label.getId())){
                        Expression<String> id = root.get("id").as(String.class);
                        predicateList.add(criteriaBuilder.equal(id,label.getId())) ;
                    }
                    if (!StringUtils.isEmpty(label.getLabelName())){
                        Expression<String> labelName = root.get("labelName").as(String.class);
                        predicateList.add(criteriaBuilder.like(labelName,"%"+label.getLabelName()+"%"));
                    }
                    //这是一种添加查询的方式
                    Predicate[] predicates = new Predicate[predicateList.size()];
                    return criteriaBuilder.and(predicateList.toArray(predicates));
                }else {
                    return null;
                }

            }            //在分页对象中去排序
        }, PageRequest.of(page -1,size,new Sort(Sort.Direction.DESC,"id")));
    }
}
