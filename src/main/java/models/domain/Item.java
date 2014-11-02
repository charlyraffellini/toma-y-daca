package models.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import models.domain.User;

/**
 * Created by Palumbo on 27/09/2014.
 */
@Entity
public class Item extends DomainObject {

    @Id public long id;
    public User owner;
    public String description;
    public String picture;

    public Item(User user, String description, String picture) {
        this.owner = user;
        this.description = description;
        this.picture = picture;
    }

    public Item(){}

    public boolean hasOwner(User user) {
        return owner == user;
    }

    public void changeOwnerTo(User user) {
        this.owner = user;
    }
}
