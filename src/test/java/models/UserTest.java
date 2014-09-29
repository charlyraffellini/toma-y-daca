package models;

import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;
import models.domain.UserWithItem;
import models.domain.exceptions.NotFriendUserException;
import models.domain.exceptions.UserDoesntHaveItemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Palumbo on 29/09/2014.
 */
public class UserTest {

    private User user = new User();
    private User friend = new User();
    private User noFriend = new User();

    @Before
    public void initializeUsers(){
        user.addFriend(friend);
    }

    @Test(expected = NotFriendUserException.class)
    public void testCanValidateAFriendlyUser(){
        this.user.validateFriend(noFriend);
    }

    @Test(expected = UserDoesntHaveItemException.class)
    public void testCanValidateHisItems(){
        this.user.validateItem(new Item(friend, "", ""));
    }

    @Test(expected = UserDoesntHaveItemException.class)
    public void testCanNotGetAnOtherUsersItem(){
        Item noFriendItem = new Item(noFriend, "Friend Item", "Img1");
        this.friend.getWithItem(noFriendItem);
    }

    @Test
    public void testCanSendATradeRequestToAFriend(){
        Item friendItem = new Item(friend, "Friend Item", "Img1");
        UserWithItem friendWithItem = this.friend.getWithItem(friendItem);
        Item userItem = new Item(user, "User Item", "Img2");

        TradeRequest tradeRequest = this.user.sendTrade(userItem, friendWithItem);

        UserWithItem sender = tradeRequest.sender;
        assertEquals(user, sender.user);
        assertEquals(userItem, sender.item);
        UserWithItem receiver = tradeRequest.receiver;
        assertEquals(friend, receiver.user);
        assertEquals(friendItem, receiver.item);
    }

    @Test(expected = NotFriendUserException.class)
    public void testCanNotSendATradeRequestToANoFriendUser(){
        Item noFriendItem = new Item(noFriend, "Friend Item", "Img1");
        UserWithItem noFriendWithItem = this.noFriend.getWithItem(noFriendItem);
        Item userItem = new Item(user, "User Item", "Img2");

        this.user.sendTrade(userItem, noFriendWithItem);
    }

    @Test(expected = UserDoesntHaveItemException.class)
    public void testCanNotSendATradeRequestFromANotOwnerItem(){
        Item friendItem = new Item(friend, "Friend Item", "Img1");
        UserWithItem friendWithItem = this.friend.getWithItem(friendItem);
        Item noUserItem = new Item(noFriend, "User Item", "Img2");

        this.user.sendTrade(noUserItem, friendWithItem);
    }
}
