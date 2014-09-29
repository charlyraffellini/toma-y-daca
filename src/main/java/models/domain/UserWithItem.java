package models.domain;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class UserWithItem extends DomainObject{
    public final User user;
    public final Item item;

    public UserWithItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }
}
