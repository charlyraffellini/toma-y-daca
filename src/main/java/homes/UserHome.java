package homes;

import models.domain.User;

/**
 * Not Created by Palumbo on 27/09/2014.
 */
public class UserHome extends Home<User,PersistentUser> {

    @Override
    protected PersistentUser transform(User user) {
        PersistentUser persUser = new PersistentUser();
        persUser.id=user.id;
        persUser.oauth_token=user.oauth_token;
        persUser.fullname=user.fullname;
        for (Long friend : user.friendIds) {
            persUser.friendIds.add(friend);
        }

        return persUser;
    }

    @Override
    protected User transform(PersistentUser persistentUser) {
        User user = new User();
        user.id=persistentUser.id;
        user.oauth_token=persistentUser.oauth_token;
        user.fullname=persistentUser.fullname;
        for (Long friendId : persistentUser.friendIds) {
            user.addFriend(friendId);
        }
        return user;
    }

    @Override
    protected Class<PersistentUser> persistentType() {
        return PersistentUser.class;
    }
}
