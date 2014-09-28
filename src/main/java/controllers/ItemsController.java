package controllers;

import com.google.inject.Inject;
import models.domain.Item;
import models.domain.User;
import models.dtos.ItemCreateDTO;
import models.homes.ItemHome;
import models.homes.UserHome;
import models.integrations.Listing;
import models.integrations.MeliApi;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsController {

    @Inject
    MeliApi meliApi;
    @Inject
    ItemHome itemHome;
    @Inject
    UserHome userHome;

    public Result createItem(Session session, ItemCreateDTO itemCreateDTO) {
        User user = this.getUser(session);

        Listing listing = this.meliApi.getListing(itemCreateDTO.meliId);

        Item item = new Item(user, listing.description, listing.picture);
        int id = this.itemHome.create(item);

        return Results.json().render(id);
    }

    private User getUser(Session session) {
        return new User(); //this.userHome.get(Integer.parseInt(session.getId()));
    }

}
