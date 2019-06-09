package ml.weiyan.friends.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author misterWei
 * @create 2019年06月09号:16点36分
 * @mailbox mynameisweiyan@gmail.com
 */
@Entity
@Table(name = "tb_friend")
public class Friend implements Serializable{

    @Id
    @Column(name = "userid")
    private String userId;

    @Column(name = "friendid")
    private String friendId;

    @Column(name = "islike")
    private String islike;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
