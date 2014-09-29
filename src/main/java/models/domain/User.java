package models.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class User extends DomainObject{

    private Collection<User> users = new ArrayList<>();

    public void addFriend(User user) {
        this.users.add(user);
    }
}
