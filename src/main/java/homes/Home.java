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

    private HashMap<Long, TEntity> entities = new HashMap<>();

    public Home() {this.ofy = ObjectifyService.ofy();

    }

    public long create(TEntity entity) {
        entity.id = this.getNextId();

        this.ofy.save().entity(this.transform(entity)).now();

        return entity.id;
    }


    public Collection<TEntity> getAll() {
        return Collections2.transform(this.ofy.load().type(this.persistentType()).list(), new Function<TPersistent, TEntity>() {
            @Override
            public TEntity apply(TPersistent entity) {
                return transform(entity);
            }
        }) ;
    }

    public TEntity get(long entityId) {
        Loader asdf =  ObjectifyService.ofy().load();
        LoadType<TPersistent> asdfg = asdf.type(this.persistentType());
        TPersistent asdfgh = asdfg.id(entityId).now();
//        int count = asdfgh.list().size();
//        TPersistent asd = asdfgh.list().get(0);
        return this.transform(asdfgh);
    }

    public void update(TEntity entity) {
        this.ofy.save().entity(this.transform(entity)).now();
    }

    public void delete(TEntity entity) {
        entities.remove(entity.id);
    }

    public long getNextId() {
        return this.ofy.load().type(this.persistentType()).count() + 1;
    }

    protected abstract TPersistent transform(TEntity entity);
    protected abstract TEntity  transform(TPersistent persistent);
    protected abstract Class<TPersistent> persistentType();

}
