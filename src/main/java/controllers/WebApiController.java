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
    protected UserHome userHome;

    protected WebApiController(UserHome userHome) {
        this.userHome = userHome;
    }

    protected User getUser(Session session) { //TODO: Poner este metodo en la Session.
        long userId = Long.parseLong(session.get("userId"));
        return this.userHome.get(userId);
    }

    protected List<User> getUsers() { //TODO: Poner este metodo en la Session.
        Objectify ofy = ObjectifyService.ofy();
        return ofy.load().type(User.class).list();
    }
}
