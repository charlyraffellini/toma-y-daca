package controllers;

import com.google.inject.Inject;
import dtos.AddFriendDTO;
import homes.UserHome;
import models.domain.User;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

import java.util.List;

/**
 * Created by Federico on 01/11/14.
 */
public class UserController extends WebApiController{
    @Inject
    public UserController(UserHome userHome) {
        super(userHome);
    }

    public Result addFriend(AddFriendDTO addFriendDTO, Session session){
        User user = this.getUser(session);
        User friend = this.userHome.get(addFriendDTO.friendId);

        user.addFriend(friend);
        this.userHome.update(user);

        return Results.json().render("ok");
    }

    public Result getFriends(Session session){
        User user = this.getUser(session);
        return Results.json().render(user.friends);
    }

    public Result getAllUsers(Session session){
        List<User> users = this.getUsers();
        return Results.json().render(users);
    }

    public Result getMe(Session session){
        return Results.json().render(this.getUser(session));
    }
}
