package ml.weiyan.friends.dao;

import ml.weiyan.friends.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author misterWei
 * @create 2019年06月09号:18点23分
 * @mailbox mynameisweiyan@gmail.com
 */
@Repository
public interface NoFriendDao extends JpaRepository<NoFriend,String>{
    public NoFriend findByUserIdAndAndFriendId(String userId, String friendId);

}
