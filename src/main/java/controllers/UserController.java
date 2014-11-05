package controllers;

import com.google.inject.Inject;
import homes.UserHome;
import models.domain.User;
import ninja.Result;
import ninja.Results;

import java.util.List;

/**
 * Created by Federico on 01/11/14.
 */
public class UserController extends WebApiController{
    @Inject
    public UserController(UserHome userHome) {
        super(userHome);
    }

    public Result getAllUsers(){
        List<User> users = this.getUsers();
        return Results.json().render(users);

    }
}
