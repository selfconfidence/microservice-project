package ml.weiyan.recruit.dao;

import ml.weiyan.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{


    /**这种写法的意思就是查询最上的6条数据,举一反三以此类推
     *  public List<Enterprise> findTop6By
     * @param ishot
     * @return
     */
	public List<Enterprise> findByIshot(String ishot);
}
