package models.domain;

import com.googlecode.objectify.annotation.Entity;
import models.domain.exceptions.TradeDoesntBelongToUser;

/**
 * Created by Palumbo on 29/09/2014.
 */
@Entity
public class TradeRequest extends DomainObject{
    public final UserWithItem sender;
    public final UserWithItem receiver;

    public TradeRequest(UserWithItem sender, UserWithItem receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void accept() {
        this.validate();
        this.changeItems();
    }

    private void changeItems() {
        this.sender.item.changeOwnerTo(this.receiver.user);
        this.receiver.item.changeOwnerTo(this.sender.user);
    }

    private void validate() {
        this.sender.validate();
        this.receiver.validate();
    }

    public void validateOwner(User user) {
        if (this.receiver.user != user)
            throw new TradeDoesntBelongToUser(this, user);
    }
}
