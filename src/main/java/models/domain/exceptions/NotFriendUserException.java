package models.domain.exceptions;

import models.domain.User;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class NotFriendUserException extends RuntimeException {
    private User friend;

    public NotFriendUserException(User friend) {
        this.friend = friend;
    }
}
