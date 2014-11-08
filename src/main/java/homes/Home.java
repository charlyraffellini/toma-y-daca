package homes;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import models.domain.DomainObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Palumbo on 27/09/2014.
 */
public abstract class Home<TEntity extends DomainObject> {

    private final Objectify ofy;

    private long nextId = 1;
    private HashMap<Long, TEntity> entities = new HashMap<>();

    public Home() {
        this.ofy = ObjectifyService.ofy();
    }

    public long create(TEntity entity) {
        long id = nextId;
        nextId ++;

        entity.id = id;
        entities.put(id, entity);

        return id;
    }

    public Collection<TEntity> getAll() {
        return this.ofy.load().type(this.entityType()).list();
    }

    public TEntity get(long entityId) {
        return this.ofy.load().type(this.entityType()).filter("id", entityId).list().get(0);
    }

    public void update(TEntity entity) {
        entities.put(entity.id, entity);
    }

    public void delete(TEntity entity) {
        entities.remove(entity.id);
    }

    public long getNextId() {
        return nextId;
    } // TODO: Este metodo y field habria que borrarlo cuando se implemente la persistencia

    protected abstract Class<TEntity> entityType();
}
