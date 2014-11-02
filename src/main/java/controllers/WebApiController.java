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
        int userId = (int)Integer.parseInt(session.get("userId"));
        return this.userHome.get(userId);
    }

}
