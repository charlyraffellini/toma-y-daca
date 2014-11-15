package models;

import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import models.domain.exceptions.TradeDoesntBelongToUser;
import models.domain.exceptions.UserDoesntHaveItemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 05/10/2014.
 */
public class TradeRequestTest {
    private User aUser = (User) new User().withId(1);
    private User otherUser = (User) new User().withId(2);
    private Item aItem;
    private Item otherItem;
    private TradeRequest trade;

    @Before
    public void createTrade(){
        aItem = new Item(aUser, "un Item", "asd");
        otherItem = new Item(otherUser, "otro Item", "dsa");

        this.trade = new TradeRequest(aUser, aItem, otherUser, otherItem);
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
