package controllers;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
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

    protected User getUser() { //TODO: Poner este metodo en la Session.
//        int userId = 3;
        int userId = (int)Integer.parseInt(session.get("userId"));
        return this.userHome.get(userId);
    }

    protected User[] getUsers() { //TODO: Poner este metodo en la Session.
        int[] userIDs = new int[10]; //Integer.parseInt(this.session.get("userId"));
        for (int i = 0; i < 10; i++) {
            userIDs[i]=i+1;
        }



        User[] users = new User[userIDs.length];
        Objectify ofy = ObjectifyService.ofy();
        for (int i = 0; i < userIDs.length; i++) {
//            users[i] = this.userHome.get(userIDs[i]);
            users[i] = ofy.load().type(User.class).id(userIDs[i]).now();

        }


        return users;
    }
}
