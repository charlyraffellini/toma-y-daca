package controllers;

import com.google.inject.Inject;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import homes.UserHome;
import models.domain.User;
import ninja.session.Session;

import javax.naming.Context;
import java.util.List;

/**
 * Created by Palumbo on 29/09/2014.
 */


public abstract class WebApiController {
//    public Session session;
    protected UserHome userHome;

    protected WebApiController(Session session, UserHome userHome) {
//        this.session = session;
        this.userHome = userHome;
    }

    protected User getUser(Session session) { //TODO: Poner este metodo en la Session.

        int userId = Integer.parseInt(session.get("userId"));
        Objectify ofy = ObjectifyService.ofy();
        User asd = ofy.load().type(User.class).filter("id ==",userId).first().now();
        return asd;
    }

    protected List<User> getUsers() { //TODO: Poner este metodo en la Session.
        Objectify ofy = ObjectifyService.ofy();
        return ofy.load().type(User.class).list();
    }
}
