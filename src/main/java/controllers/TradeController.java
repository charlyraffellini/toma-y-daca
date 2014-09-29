package controllers;

import dtos.TradeCreateDTO;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradeController {

    public Result sendTradeRequest(Session session, TradeCreateDTO tradeCreateDTO){
        return Results.ok();
    }
}
