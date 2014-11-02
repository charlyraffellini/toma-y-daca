package models.domain;

import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class User extends DomainObject{

    private List<User> friends;

    public User()
    {
        this.friends = new ArrayList<User>();

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

    public List<User> getAllFriends()
    {
        return this.friends;
    }


}
