package homes;

import com.googlecode.objectify.annotation.EntitySubclass;
import models.domain.DomainObject;

@EntitySubclass(index = true)
public class PersistentItem extends DomainObject {
    public long ownerId;
    public String description;
    public String picture;
}
