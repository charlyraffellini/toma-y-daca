package models;

import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Palumbo on 29/09/2014.
 */
public class UserTest {

    private User user = (User) new User().withId(1);
    private User friend = (User) new User().withId(2);
    private User noFriend = (User) new User().withId(3);

    @Before
    public void initializeUsers(){
        user.addFriend(friend.id);
    }

    @Test(expected = NotFriendUserException.class)
    public void testCanValidateAFriendlyUser(){
        this.user.validateFriend(noFriend);
    }

    @Test
    public void testCanSendATradeRequestToAFriend(){
        Item friendItem = new Item(friend, "Friend Item", "Img1");
        Item userItem = new Item(user, "User Item", "Img2");

        TradeRequest tradeRequest = this.user.sendTrade(userItem, friend, friendItem);

        assertEquals(user, tradeRequest.senderUser);
        assertEquals(userItem, tradeRequest.senderItem);
        assertEquals(friend, tradeRequest.receiverUser);
        assertEquals(friendItem, tradeRequest.receiverItem);
    }

    @Test(expected = NotFriendUserException.class)
    public void testCanNotSendATradeRequestToANoFriendUser(){
        Item noFriendItem = new Item(noFriend, "Friend Item", "Img1");
        Item userItem = new Item(user, "User Item", "Img2");

        this.user.sendTrade(userItem, noFriend, noFriendItem);
    }

    @Test(expected = UserDoesntHaveItemException.class)
    public void testCanNotSendATradeRequestFromANotOwnerItem(){
        Item friendItem = new Item(friend, "Friend Item", "Img1");
        Item noUserItem = new Item(noFriend, "User Item", "Img2");

        this.user.sendTrade(noUserItem, friend, friendItem);
    }
}
