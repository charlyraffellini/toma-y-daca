package homes;

import com.google.inject.Inject;
import models.domain.TradeRequest;

/**
 * Not Created by Palumbo on 27/09/2014.
 */
public class TradeHome extends Home<TradeRequest,PersistentTrade> {
    @Inject
    UserHome userHome;
    @Inject
    ItemHome itemHome;

    @Override
    protected PersistentTrade transform(TradeRequest trade) {
        PersistentTrade persTrade = new PersistentTrade();

        persTrade.id=trade.id;

        persTrade.receiverItemId = trade.receiverItem.id;
        persTrade.receiverUserId = trade.receiverUser.id;

        persTrade.senderItemId = trade.senderItem.id;
        persTrade.senderUserId = trade.senderUser.id;

        return persTrade;
    }

    @Override
    protected TradeRequest transform(PersistentTrade persistentTrade) {
        TradeRequest trade = new TradeRequest();

        trade.id=persistentTrade.id;

        trade.receiverItem = itemHome.get(persistentTrade.receiverItemId);
        trade.receiverUser=userHome.get(persistentTrade.receiverUserId);

        trade.senderItem = itemHome.get(persistentTrade.senderItemId);
        trade.senderUser=userHome.get(persistentTrade.senderUserId);

        return trade;
    }

    @Override
    protected Class<PersistentTrade> persistentType() {
        return PersistentTrade.class;
    }
}
