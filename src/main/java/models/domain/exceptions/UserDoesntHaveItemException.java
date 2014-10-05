package models.domain.exceptions;

import models.domain.Item;
import models.domain.User;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class UserDoesntHaveItemException extends RuntimeException {
    private final User user;
    private final Item item;

    public UserDoesntHaveItemException(User user, Item item) {
        this.user = user;
        this.item = item;
    }
}
