package models.homes;

import models.domain.DomainObject;

import java.util.List;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class Home<TEntity extends DomainObject> {

    private int nextId = 1;
    private List<TEntity> entities;

    public int create(TEntity entity) {
        int id = nextId;
        nextId ++;

        entity.id = id;
        entities.add(id, entity);

        return id;
    }

    public List<TEntity> getAll() {
        return entities;
    }

    public TEntity get(int entityId) {
        return entities.get(entityId);
    }

    public void update(TEntity entity) {
        entities.set(entity.id, entity);
    }

    public void delete(TEntity entity) {
        entities.remove(entity);
    }
}
