package controllers;

import homes.UserHome;
import models.domain.User;
import ninja.session.Session;

/**
 * Created by Palumbo on 29/09/2014.
 */
public abstract class WebApiController {
    protected Session session;
    protected UserHome userHome;

    protected WebApiController(Session session, UserHome userHome) {
        this.session = session;
        this.userHome = userHome;
    }

    protected User getUser() { //TODO: Poner este método en la Session.
        int userId = 1; //Integer.parseInt(this.session.get("userId"));
        return this.userHome.get(userId);
    }

    protected User[] getUsers() { //TODO: Poner este método en la Session.
        int[] userIDs = new int[2]; //Integer.parseInt(this.session.get("userId"));
        userIDs[0]=1;
        userIDs[1]=2;

        User[] users = new User[userIDs.length];
        for (int i = 0; i < userIDs.length; i++) {
            users[i] = this.userHome.get(userIDs[i]);
        }


        return users;
    }
}
