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
//        Listing listing = this.listingsApi.getListing("MLA527664161");

        Item item = new Item(user, listing.description, listing.picture);
        int id = this.itemHome.create(item);

        return Results.json().render(id);
    }

    public Result getAllItems(Session session) {
        User user = this.getUser(session);

        Collection<Item> items = this.itemHome.getAllItemsOf(user);

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
