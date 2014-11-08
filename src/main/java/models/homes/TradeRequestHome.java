package models.homes;

import homes.Home;
import models.domain.DomainObject;
import models.domain.TradeRequest;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradeRequestHome extends Home<TradeRequest, TradeRequest>{

    @Override
    protected TradeRequest transform(TradeRequest persistent) {
        return persistent;
    }

    @Override
    protected Class<TradeRequest> persistentType() {
        return TradeRequest.class;
    }
}
