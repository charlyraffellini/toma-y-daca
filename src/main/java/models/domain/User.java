package models.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */

@Entity
public class User extends DomainObject{

    @Id
    public long id;
    public String uid;
    public String oauth_token;
    public String fullname;
    public boolean isAdmin;

    private Collection<User> friends = new ArrayList<>();

    public User(String uid,String oauth_token, String fullname) {
        this.uid = uid;
        this.oauth_token=oauth_token;
        this.fullname = fullname;
    }

    public User(){

    }

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public TradeRequest sendTrade(Item item, User friend, Item friendItem) {
        this.validateFriend(friend);

        return new TradeRequest(this.getWithItem(item), friend.getWithItem(friendItem));
    }

    public UserWithItem getWithItem(Item item) {
        this.validateItem(item);

        return new UserWithItem(this, item);
    }

    public void validateFriend(User friend) {
        if (!friends.contains(friend))
            throw new NotFriendUserException(friend);
    }

    public void validateItem(Item item) {
        if (!item.hasOwner(this))
            throw new UserDoesntHaveItemException(this, item);
    }
}
