package homes;

import models.domain.DomainObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class Home<TEntity extends DomainObject> {

    private long nextId = 1;

    private HashMap<Long, TEntity> entities = new HashMap<>();

    public long create(TEntity entity) {
        long id = nextId;
        nextId ++;

        entity.id = id;
        entities.put(id, entity);

        return id;
    }

    public Collection<TEntity> getAll() {
        return entities.values();
    }

    public TEntity get(int entityId) {
        return entities.get(entityId);
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
}
