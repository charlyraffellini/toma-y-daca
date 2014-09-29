package models.domain;

import models.domain.exceptions.NotUserFriendException;

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

    public void validateFriend(User friend) {
        if (!friends.contains(friend))
            throw new NotUserFriendException(friend);
    }
}
