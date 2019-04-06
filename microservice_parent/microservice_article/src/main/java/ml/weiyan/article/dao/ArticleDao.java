package ml.weiyan.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ml.weiyan.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    /**
     * 为文章进行点赞
     */
    @Modifying
    @Query(value = "UPDATE tb_article set thumbup = thumbup+1 WHERE id = ?1",nativeQuery = true)
    public void thumbup(String articleId);

    /**
     * examine/{articleId}文章审核
     */
    @Modifying
    @Query(value = "UPDATE tb_article set state = 1 WHERE id =?1",nativeQuery = true)
    public void examine (String articleId);

}
