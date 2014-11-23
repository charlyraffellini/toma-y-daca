package controllers;

import com.google.inject.Inject;
import dtos.TradeCreateDTO;
import dtos.TradeExecuteDTO;
import homes.ItemHome;
import homes.TradeHome;
import homes.UserHome;
import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import models.homes.TradeRequestHome;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradesController extends WebApiController{

    private ItemHome itemHome;
    private TradeHome tradeHome;

    @Inject
    public TradesController(UserHome userHome, ItemHome itemHome, TradeHome tradeHome) {
        super(userHome);
        this.itemHome = itemHome;
        this.tradeHome = tradeHome;
    }

    public Result sendTradeRequest(TradeCreateDTO tradeCreateDTO, Session session){
        User friend = this.userHome.get(tradeCreateDTO.friendId);
        Item friendItem = this.itemHome.get(tradeCreateDTO.friendItemId);
        Item userItem = this.itemHome.get(tradeCreateDTO.userItemId);

        TradeRequest tradeRequest = this.getUser(session).sendTrade(userItem, friend, friendItem);

        long id = this.tradeHome.create(tradeRequest);

        return Results.json().render(id);
    }

    public Result executeTradeRequest(@PathParam("tradeId") int tradeId, TradeExecuteDTO tradeExecuteDTO, Session session){
        TradeRequest trade = this.tradeHome.get(tradeId);
        trade.validateOwner(this.getUser(session));

        if (tradeExecuteDTO.response.equals("accepted")) {
            trade.accept();
        }

        this.itemHome.update(trade.receiverItem);
        this.itemHome.update(trade.senderItem);

        this.tradeHome.delete(trade);

        return Results.json().render("ok");
    }
}
