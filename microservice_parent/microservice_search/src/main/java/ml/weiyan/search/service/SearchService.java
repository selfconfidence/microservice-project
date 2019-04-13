package ml.weiyan.search.service;

import ml.weiyan.search.dao.SearchDao;
import ml.weiyan.search.pojo.ArticleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author misterWei
 * @create 2019年04月13号:22点02分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;

    public void save(ArticleSearch articleSearch){
        searchDao.save(articleSearch);
    }

    public Page findByKeyWord(String key, int page, int size) {
     return searchDao.findByTitleLikeOrContentLike(key,key, PageRequest.of(page - 1,size));
    }
}
