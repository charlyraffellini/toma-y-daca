package controllers;

import com.google.inject.Inject;
import dtos.AddFriendDTO;
import homes.UserHome;
import models.domain.User;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

import java.util.ArrayList;
import java.util.Collection;
import models.domain.exceptions.UserDoesntExistsInDBException;

/**
 * Created by Federico on 01/11/14.
 */
public class UserController extends WebApiController{
    @Inject
    public UserController(UserHome userHome) {
        super(userHome);
    }

    public Result addFriend(AddFriendDTO addFriendDTO, Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }
        User user = this.getUser(session);
        
        //valido que exista el usuario como registrado en la base
        if( !this.userHome.exists(addFriendDTO.friendId))
            throw new UserDoesntExistsInDBException(addFriendDTO.friendId);
        
        user.addFriend(addFriendDTO.friendId);
        this.userHome.update(user);

        return Results.json().render("ok");
    }

    public Result getFriends(Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }
        User me = this.getUser(session);
        Collection<Long> friendIds = me.friendIds;
        Collection<User> friends = new ArrayList<>();
        for (Long friendId : friendIds) {
            friends.add(this.userHome.get(friendId));
        }

        return Results.json().render(this.transformUsers(friends));
    }

    public Result getAllUsers(Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }
        Collection<User> users = this.getUsers();
        return Results.json().render(this.transformUsers(users));
    }

    public Result getMe(Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }
        return Results.json().render(this.transform(this.getUser(session)));
    }
}
