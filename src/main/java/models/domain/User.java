package models.domain;

import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class User extends DomainObject{

    private Collection<User> friends = new ArrayList<>();

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public TradeRequest sendTradeRequest(Item item, UserWithItem friendWithItem) {
        this.validateFriend(friendWithItem.user);

        return new TradeRequest(this.getWithItem(item), friendWithItem);
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
