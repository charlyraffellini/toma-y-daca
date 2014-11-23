package homes;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;
import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;

import java.util.Collection;

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

    public Collection<TradeRequest> getAllTradesOf(final User user) {
        Collection<TradeRequest> tradelist = this.getAll();

        return Collections2.filter(tradelist,
                new Predicate<TradeRequest>() {
                    @Override
                    public boolean apply(TradeRequest trade) {
                        return trade.receiverUser.id == user.id;

                    }
                }
        );

    }
}
