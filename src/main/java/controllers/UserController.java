package controllers;

import com.google.inject.Inject;
import dtos.ItemCreateDTO;
import homes.ItemHome;
import homes.UserHome;
import models.domain.Item;
import models.domain.User;
import models.integrations.Listing;
import models.integrations.ListingsApi;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;

import java.util.Collection;
import java.util.List;

/**
 * Created by jsorella on 01/11/2014.
 */
    public class UserController extends WebApiController{

        private ListingsApi listingsApi;
        private ItemHome itemHome;

        @Inject
        public UserController(Session session, UserHome userHome) {
            super(session, userHome);
        }


        public Result getAllFriends() {
            List<User> friends = this.getUser().getAllFriends();

            return Results.json().render(friends);
        }
        
        public Result getAllUsers(Session session){

       	    List<User> users = this.getUsers();
            return Results.json().render(users);
        }

    }


