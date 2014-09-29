package models.domain.exceptions;

import models.domain.User;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class NotUserFriendException extends RuntimeException {
    private User friend;

    public NotUserFriendException(User friend) {
        this.friend = friend;
    }
}
