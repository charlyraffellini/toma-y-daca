package homes;

import models.domain.User;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class UserHome extends Home<User> {
    @Override
    protected Class<User> entityType() {
        return User.class;
    }
}
