package models.domain;

import com.googlecode.objectify.annotation.*;
import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */

@EntitySubclass(index=true)
public class User extends DomainObject{

    public String oauth_token;
    public String fullname;
    public Collection<Long> friendIds = new ArrayList<>();

    public User(){ }

    public void addFriend(Long id) {
        this.friendIds.add(id);
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
        if (!friendIds.contains(friend.id))
            throw new NotFriendUserException(friend);
    }

    public void validateItem(Item item) {
        if (!item.hasOwner(this))
            throw new UserDoesntHaveItemException(this, item);
    }
}
