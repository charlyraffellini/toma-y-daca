package controllers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;
import dtos.ItemCreateDTO;
import dtos.ItemDTO;
import homes.ItemHome;
import homes.UserHome;
import models.domain.Item;
import models.domain.User;
import models.integrations.FacebookAPI;
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
    private FacebookAPI facebookAPI;

    @Inject
    public ItemsController(UserHome userHome, ListingsApi listingsApi, ItemHome itemHome, FacebookAPI facebookAPI) {
        super(userHome);
        this.listingsApi = listingsApi;
        this.itemHome = itemHome;
        this.facebookAPI = facebookAPI;
    }

    public Result createItem(ItemCreateDTO itemCreateDTO, Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }

        User user = this.getUser(session);

        Listing listing = this.listingsApi.getListing(itemCreateDTO.meliId);

        Item item = new Item(user, listing.description, listing.picture);
        long id = this.itemHome.create(item);

        if (itemCreateDTO.wallPost) {
            this.facebookAPI.postNewItem(user, item);
        }

        return Results.json().render(id);
    }

    public Result getAllItems(Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }

        User user = this.getUser(session);

        Collection<Item> items = this.itemHome.getAllItemsOf(user);

        return Results.json().render(this.transformItems(items));
    }

    public Result getFriendItems(@PathParam("friendId") long friendId, Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }

        User user = this.getUser(session);
        User friend = this.userHome.get(friendId);

        user.validateFriend(friend);

        Collection<Item> items = this.itemHome.getAllItemsOf(friend);

        return Results.json().render(this.transformItems(items));
    }

    public Result deleteItem(@PathParam("itemId") int itemId, Session session)
    {
        if(this.validateSessionExists(session)){
            return this.redirect();
        }

        Item item = itemHome.get(itemId);
        itemHome.delete(item);

        return Results.json().render("ok");
    }
}
