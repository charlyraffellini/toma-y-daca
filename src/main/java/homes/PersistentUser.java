package homes;

import com.googlecode.objectify.annotation.EntitySubclass;
import models.domain.DomainObject;


import java.util.ArrayList;
import java.util.Collection;

@EntitySubclass(index = true)
public class PersistentUser extends DomainObject {
    public String oauth_token;
    public String fullname;
    public Collection<Long> friendIds = new ArrayList<>();

}
