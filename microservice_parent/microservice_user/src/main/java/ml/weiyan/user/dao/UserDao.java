package ml.weiyan.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ml.weiyan.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	public User findByNickname(String nickname);

	//增加粉丝
	@Modifying
	@Query(nativeQuery = true,value = "update tb_user SET fanscount = fanscount + ?2 WHERE id = ?1")
	public void updateFanscount(String userId,int flag);

	//增加关注
	@Modifying
	@Query(nativeQuery = true,value = "update tb_user SET followcount = followcount + ?2 WHERE id = ?1")
	public void updateFollowcount(String userId,int flag);
}
