package models.domain;

import com.googlecode.objectify.annotation.Entity;
import models.domain.exceptions.TradeDoesntBelongToUser;
import models.domain.exceptions.UserDoesntHaveItemException;

/**
 * Created by Palumbo on 29/09/2014.
 */
@Entity
public class TradeRequest extends DomainObject{
    public User senderUser;
    public Item senderItem;

    public User receiverUser;
    public Item receiverItem;

    public TradeRequest(){}

    public TradeRequest(User senderUser, Item senderItem, User receiverUser, Item receiverItem) {
        this.receiverUser = receiverUser;
        this.receiverItem = receiverItem;
        this.senderUser = senderUser;
        this.senderItem = senderItem;
    }

    public void accept() {
        this.validate();
        this.changeItems();
    }

    private void changeItems() {
        this.senderItem.changeOwnerTo(this.receiverUser);
        this.receiverItem.changeOwnerTo(this.senderUser);
    }

    private void validate() {
        this.receiverItem.validateOwner(this.receiverUser);
        this.senderItem.validateOwner(this.senderUser);
    }

    public void validateOwner(User user) {
        if (this.receiverUser.id != user.id)
            throw new TradeDoesntBelongToUser(this, user);
    }
}
