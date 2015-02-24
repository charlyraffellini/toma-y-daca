package models.domain.exceptions;


/**
 * Created by Javier on 23/02/2015.
 */
public class UserDoesntExistsInDBException extends RuntimeException {

    private Long userId;

    public UserDoesntExistsInDBException(Long userId) {
        this.userId = userId;
    }

}
