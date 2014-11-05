package homes;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import models.domain.DomainObject;

import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public abstract class Home<TEntity extends DomainObject> {

    private final Objectify ofy;

    protected Home() {
        this.ofy = ObjectifyService.ofy();
    }

    public long create(TEntity entity) {
        long id = this.getLoader().count();

        entity.id = id;
        ofy.save().entity(entity).now();

        return id;
    }

    public Collection<TEntity> getAll() {
        return this.getLoader().list();
    }

    public TEntity get(int entityId) {
        return this.getLoader().filter("id ==",entityId).first().now();
    }

    public void update(TEntity entity) { ofy.save().entity(entity).now(); }

    public void delete(TEntity entity) { ofy.delete().entity(entity).now(); }

    private LoadType<TEntity> getLoader() {
        return ofy.load().type(this.entityClass());
    }

    protected abstract Class<TEntity> entityClass();
}
