package controllers;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import homes.UserHome;
import models.domain.User;
import ninja.session.Session;

import java.util.List;

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
       //int userId = 1;
        int userId = (int)Integer.parseInt(session.get("userId"));
        return this.userHome.get(userId);
    }

    protected List<User> getUsers() { //TODO: Poner este metodo en la Session.
        Objectify ofy = ObjectifyService.ofy();
        return ofy.load().type(User.class).list();
    }
}
