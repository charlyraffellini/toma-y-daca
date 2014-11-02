package controllers;

import com.google.inject.Inject;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
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
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsController extends WebApiController{

    private ListingsApi listingsApi;
    private ItemHome itemHome;

    @Inject
    public ItemsController(Session session, UserHome userHome, ListingsApi listingsApi, ItemHome itemHome) {
        super(session, userHome);
        this.listingsApi = listingsApi;
        this.itemHome = itemHome;
    }

    public Result createItem(ItemCreateDTO itemCreateDTO, Session session) {
        User user = this.getUser(session);

        Listing listing = this.listingsApi.getListing(itemCreateDTO.meliId);
        Objectify ofy = ObjectifyService.ofy();
        Item item = new Item(user, listing.description, listing.picture);
        item.id = ofy.load().type(Item.class).count()+10;
        ofy.save().entity(item).now();
        return Results.json().render(item.id);
    }

    public Result getAllItems(Session session) {
        User user = this.getUser(session);
        Objectify ofy = ObjectifyService.ofy();
        List<Item> items = ofy.load().type(Item.class).list();

        return Results.json().render(items);
    }

    public Result getFriendItems(@PathParam("userId") int friendId, Session session) {
        User user = this.getUser(session);
        User friend = this.userHome.get(friendId);

        user.validateFriend(friend);

        Collection<Item> items = this.itemHome.getAllItemsOf(friend);

        return Results.json().render(items);
    }
}
