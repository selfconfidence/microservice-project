package ml.weiyan.friends.service;

import ml.weiyan.friends.dao.FriendDao;
import ml.weiyan.friends.dao.NoFriendDao;
import ml.weiyan.friends.pojo.Friend;
import ml.weiyan.friends.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author misterWei
 * @create 2019年06月09号:18点22分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriendId(String userId, String friendid) {

        Friend friend = friendDao.findByUserIdAndAndFriendId(userId, friendid);
        if (friend != null) {
            return 0;
        }
        //如果当前用户没有喜欢该好友,那么就添加一条记录 islike 为0 单向喜欢
        friend = new Friend();
        friend.setIslike("0");
        friend.setFriendId(friendid);
        friend.setUserId(userId);
        friendDao.save(friend);
        //然后再次查询 查看喜欢的那个人是否关联到了当前用户,如果关联了,就是双向喜欢,islike都为1
        Friend friendEnity = friendDao.findByUserIdAndAndFriendId(friendid, userId);
        if (friendEnity != null) {
            //执行更改sql
            friendDao.updateIslike("1",userId,friendid);
            friendDao.updateIslike("1",friendid,userId);
        }
        return 1;
    }

    public int addNoFriendId(String userId, String friendid) {
        NoFriend noFriend = noFriendDao.findByUserIdAndAndFriendId(userId, friendid);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();

        noFriend.setFriendId(friendid);
        noFriend.setUserId(userId);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendid) {
        //删除
        friendDao.deleteByUserIdAndFriendId(userId,friendid);
        //把好友那边的islike改为0 单向喜欢
        friendDao.updateIslike("0",friendid,userId);
        NoFriend noFriend = new NoFriend();
        noFriend.setUserId(userId);
        noFriend.setFriendId(friendid);
        noFriendDao.save(noFriend);
    }
}
