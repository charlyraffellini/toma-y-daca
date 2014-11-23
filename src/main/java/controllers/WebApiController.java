package controllers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import dtos.ItemDTO;
import dtos.TradeRequestDTO;
import dtos.UserDTO;
import homes.UserHome;
import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import ninja.session.Session;

import java.util.Collection;
import java.util.List;

/**
 * Created by Palumbo on 29/09/2014.
 */
public abstract class WebApiController {
    protected UserHome userHome;

    protected WebApiController(UserHome userHome) {
        this.userHome = userHome;
    }

    protected User getUser(Session session) { //TODO: Poner este metodo en la Session.
        long userId = Long.parseLong(session.get("userId"));
        return this.userHome.get(userId);
    }

    protected Collection<User> getUsers() { //TODO: Poner este metodo en la Session.
        Objectify ofy = ObjectifyService.ofy();
        return this.userHome.getAll();
    }

    protected Collection<ItemDTO> transformItems(Collection<Item>  items) {
        return Collections2.transform(items, new Function<Item, ItemDTO>() {
            @Override
            public ItemDTO apply(Item item) {
                return transform(item);
            }
        });
    }

    protected ItemDTO transform(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.id = item.id;
        dto.description = item.description;
        dto.picture = item.picture;
        dto.owner = this.transform(item.owner);
        return dto;
    }

    protected Collection<UserDTO> transformUsers(Collection<User> users) {
        return Collections2.transform(users, new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User user) {
                return transform(user);
            }
        });
    }

    protected UserDTO transform(User user) {
        UserDTO dto = new UserDTO();
        dto.id = user.id;
        dto.fullname = user.fullname;
        return dto;
    }

    protected Collection<TradeRequestDTO> transformTrades(Collection<TradeRequest> trades) {
        return Collections2.transform(trades, new Function<TradeRequest, TradeRequestDTO>() {
            @Override
            public TradeRequestDTO apply(TradeRequest trade) {
                return transform(trade);
            }
        });
    }

    protected TradeRequestDTO transform(TradeRequest trade) {
        TradeRequestDTO dto = new TradeRequestDTO();

        dto.senderUser = this.transform(trade.senderUser);
        dto.senderItem = this.transform(trade.senderItem);

        dto.receiverUser = this.transform(trade.receiverUser);
        dto.receiverItem = this.transform(trade.receiverItem);

        return dto;
    }
}

