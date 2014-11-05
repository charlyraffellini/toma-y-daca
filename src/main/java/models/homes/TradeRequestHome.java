package models.homes;

import homes.Home;
import models.domain.TradeRequest;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradeRequestHome extends Home<TradeRequest>{
    @Override
    protected Class<TradeRequest> entityClass() {
        return TradeRequest.class;
    }
}
