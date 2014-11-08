package homes;

import models.domain.User;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class UserHome extends Home<User, User> {
    @Override
    protected User transform(User persistent) {
        return persistent;
    }

    @Override
    protected Class<User> persistentType() {
        return User.class;
    }
}
