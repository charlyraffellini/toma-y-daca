package models.domain.exceptions;

import models.domain.TradeRequest;
import models.domain.User;

/**
 * Created by Palumbo on 05/10/2014.
 */
public class TradeDoesntBelongToUser extends RuntimeException {
    public TradeDoesntBelongToUser(TradeRequest tradeRequest, User user) {
    }
}
