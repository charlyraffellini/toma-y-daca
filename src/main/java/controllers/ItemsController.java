package controllers;

import com.google.inject.Inject;
import dtos.ItemCreateDTO;
import homes.ItemHome;
import homes.UserHome;
import models.domain.Item;
import models.domain.User;
import models.integrations.Listing;
import models.integrations.MeliApi;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;

import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsController extends WebApiController{

    private MeliApi meliApi;
    private ItemHome itemHome;

    @Inject
    protected ItemsController(MeliApi meliApi, ItemHome itemHome, Session session, UserHome userHome) {
        super(session, userHome);
        this.meliApi = meliApi;
        this.itemHome = itemHome;
    }

    public Result createItem(ItemCreateDTO itemCreateDTO) {
        User user = this.getUser();

        Listing listing = this.meliApi.getListing(itemCreateDTO.meliId);

        Item item = new Item(user, listing.description, listing.picture);
        int id = this.itemHome.create(item);

        return Results.json().render(id);
    }

    public Result getAllItems(Session session) {
        User user = this.getUser();

        Collection<Item> items = this.itemHome.getAllItemsOf(user);

        return Results.json().render(items);
    }

    public Result getFriendItems(@PathParam("userId") int friendId) {
        User user = this.getUser();
        User friend = this.userHome.get(friendId);

        user.validateFriend(friend);

        Collection<Item> items = this.itemHome.getAllItemsOf(friend);

        return Results.json().render(items);
    }
}
