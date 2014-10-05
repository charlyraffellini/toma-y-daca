package models.domain;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradeRequest extends DomainObject{
    public final UserWithItem sender;
    public final UserWithItem receiver;

    public TradeRequest(UserWithItem sender, UserWithItem receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
