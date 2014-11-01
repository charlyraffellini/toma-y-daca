package models.domain;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by Palumbo on 27/09/2014.
 */
@Entity
public class Item extends DomainObject {

    public User owner;
    public String description;
    public String picture;

    public Item(User user, String description, String picture) {
        this.owner = user;
        this.description = description;
        this.picture = picture;
    }

    public boolean hasOwner(User user) {
        return owner == user;
    }

    public void changeOwnerTo(User user) {
        this.owner = user;
    }
}