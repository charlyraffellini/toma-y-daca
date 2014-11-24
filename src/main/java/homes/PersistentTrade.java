package homes;

import com.googlecode.objectify.annotation.EntitySubclass;
import models.domain.DomainObject;


@EntitySubclass(index = true)
public class PersistentTrade extends DomainObject {
    public long senderUserId;
    public long senderItemId;

    public long receiverUserId;
    public long receiverItemId;
}
