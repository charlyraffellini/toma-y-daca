package controllers;

import com.google.inject.Inject;
import dtos.TradeCreateDTO;
import dtos.TradeExecuteDTO;
import homes.ItemHome;
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
    private TradeRequestHome tradeHome;

    @Inject
    public TradesController(Session session, UserHome userHome, ItemHome itemHome, TradeRequestHome tradeHome) {
        super(session, userHome);
        this.itemHome = itemHome;
        this.tradeHome = tradeHome;
    }

    public Result sendTradeRequest(TradeCreateDTO tradeCreateDTO, Session session){
        User friend = this.userHome.get(tradeCreateDTO.friendId);
        Item friendItem = this.itemHome.get(tradeCreateDTO.friendItemId);
        Item userItem = this.itemHome.get(tradeCreateDTO.userItemId);

        TradeRequest tradeRequest = this.getUser(session).sendTrade(userItem, friend, friendItem);

        int id = this.tradeHome.create(tradeRequest);

        return Results.json().render(id);
    }

    public Result executeTradeRequest(@PathParam("tradeId") int tradeId, TradeExecuteDTO tradeExecuteDTO, Session session){
        TradeRequest trade = this.tradeHome.get(tradeId);
        trade.validateOwner(this.getUser(session));

        if (tradeExecuteDTO.response == "accepted")
            trade.accept();

        this.tradeHome.delete(trade);
        return Results.json().render("ok");
    }
}