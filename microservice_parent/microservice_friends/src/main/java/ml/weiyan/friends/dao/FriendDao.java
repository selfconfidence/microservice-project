package ml.weiyan.friends.dao;

import ml.weiyan.friends.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author misterWei
 * @create 2019年06月09号:18点23分
 * @mailbox mynameisweiyan@gmail.com
 */
@Repository
public interface FriendDao extends JpaRepository<Friend,String>{
    public Friend findByUserIdAndAndFriendId(String userId,String friendId);

    @Modifying
    @Query(value = "update tb_friend SET islike = ? WHERE userid = ? AND friendid = ? ",nativeQuery = true)
    public void  updateIslike(String islike,String userid,String friendId);

    public void deleteByUserIdAndFriendId(String userId,String friendId);
}
