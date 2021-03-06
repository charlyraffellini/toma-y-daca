package models.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import models.domain.exceptions.UserDoesntHaveItemException;


/**
 * Created by Palumbo on 27/09/2014.
 */

public class Item extends DomainObject {

    public User owner;
    public String description;
    public String picture;

    public Item(User user, String description, String picture) {
        this.owner = user;
        this.description = description;
        this.picture = picture;
    }
    public Item (){ }

    public boolean hasOwner(User user) {
        return owner.id == user.id;
    }

    public void changeOwnerTo(User user) {
        this.owner = user;
    }

    public void validateOwner(User user) {
        if (!this.hasOwner(user))
            throw new UserDoesntHaveItemException(user, this);
    }
}
