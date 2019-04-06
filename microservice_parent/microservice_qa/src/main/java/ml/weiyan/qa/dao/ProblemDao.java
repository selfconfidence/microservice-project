package ml.weiyan.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ml.weiyan.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问答回复列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem as p WHERE p.id in (SELECT problemid from tb_pl WHERE labelid = ?1 ) ORDER BY p.replytime desc",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    /**
     * 热门问答列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem as p WHERE p.id in (SELECT problemid from tb_pl WHERE labelid = ?1 ) ORDER BY p.reply desc",nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);

    /**
     *等待回答列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem as p WHERE p.id in (SELECT problemid from tb_pl WHERE labelid = ?1 ) AND reply = 0 ORDER BY p.createtime desc",nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);
}
