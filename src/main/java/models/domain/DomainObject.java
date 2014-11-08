package models.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Palumbo on 27/09/2014.
 */
@Entity
public class DomainObject {
    @Id
    @Index
    public long id;
}
