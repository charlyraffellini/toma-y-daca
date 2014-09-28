package controllers;

import com.google.appengine.api.files.dev.Session;
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
        User user = this.userHome.get(Integer.parseInt(session.getID()));

        Listing listing = this.meliApi.getListing(itemCreateDTO.meliId);

        int id = this.itemHome.create(new Item(user, listing.description, listing.picture));

        return Results.json().render(id);
    }
}
