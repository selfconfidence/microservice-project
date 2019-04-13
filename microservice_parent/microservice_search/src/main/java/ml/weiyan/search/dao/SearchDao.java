package ml.weiyan.search.dao;

import ml.weiyan.search.pojo.ArticleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author misterWei
 * @create 2019年04月13号:22点01分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface SearchDao extends ElasticsearchRepository<ArticleSearch,String> {
 public Page findByTitleLikeOrContentLike(String title, String content, Pageable pageable);

}
