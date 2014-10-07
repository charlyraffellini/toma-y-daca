package models;

import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import models.domain.UserWithItem;
import models.domain.exceptions.TradeDoesntBelongToUser;
import models.domain.exceptions.UserDoesntHaveItemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 05/10/2014.
 */
public class TradeRequestTest {
    private User aUser = new User();
    private User otherUser = new User();
    private Item aItem;
    private Item otherItem;
    private TradeRequest trade;

    @Before
    public void createTrade(){
        aItem = new Item(aUser, "un Item", "asd");
        otherItem = new Item(otherUser, "otro Item", "dsa");

        this.trade = new TradeRequest(new UserWithItem(aUser, aItem), new UserWithItem(otherUser, otherItem));
    }

    @Test(expected = TradeDoesntBelongToUser.class)
    public void testCanValidateBelongAUser(){
        this.trade.validateOwner(this.aUser);
    }


    @Test
    public void testAcceptChangeItemsOwner(){
        this.trade.accept();

        assertEquals(this.otherUser, this.aItem.owner);
        assertEquals(this.aUser, this.otherItem.owner);
    }

    @Test(expected = UserDoesntHaveItemException.class)
    public void testAcceptValidateTheItemsOwner(){
        this.aItem.owner = this.otherUser;

        this.trade.accept();
    }

}
