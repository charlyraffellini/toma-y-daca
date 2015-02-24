package homes;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Loader;
import models.domain.DomainObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Palumbo on 27/09/2014.
 */
public abstract class Home<TEntity extends DomainObject, TPersistent> {

    protected Objectify ofy;

    public Home() { this.ofy = ObjectifyService.ofy(); }

    public long create(TEntity entity) {
        entity.id = this.getNextId();

        this.ofy.save().entity(this.transform(entity)).now();

        return entity.id;
    }


    public Collection<TEntity> getAll() {
        return Collections2.transform(this.getLoader().list(), new Function<TPersistent, TEntity>() {
            @Override
            public TEntity apply(TPersistent entity) {
                return transform(entity);
            }
        }) ;
    }

    public TEntity get(long entityId) {
        TPersistent asdfgh = this.getLoader().id(entityId).now();
        return this.transform(asdfgh);
    }

    public boolean exists(long entityId) {
        return (this.get(entityId) != null);
    }

    public void update(TEntity entity) {
        this.ofy.save().entity(this.transform(entity)).now();
    }

    public void delete(TEntity entity) {
        this.ofy.delete().entity(this.transform(entity)).now();
    }


    /**
     * toma el ID más grande del mismo tipo y le suma 1
     *
     * @return long
     */
    public long getNextId() {
        long maxId = 0;

        if(this.getLoader().count() > 0 ){
            maxId = this.transform(this.getLoader().order("-__key__").first().now()).id;
        }

        return  (maxId + 1);
    }

    protected LoadType<TPersistent> getLoader(){
        return this.ofy.load().type(this.persistentType());
    }

    protected abstract TPersistent transform(TEntity entity);
    protected abstract TEntity  transform(TPersistent persistent);
    protected abstract Class<TPersistent> persistentType();

}
