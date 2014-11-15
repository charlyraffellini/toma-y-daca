package homes;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import models.domain.Item;
import models.domain.User;
import com.google.common.collect.Collections2;

import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemHome extends Home<Item, PersistentItem> {

    @Inject
    UserHome userHome;

    public Collection<Item> getAllItemsOf(final User user) {
        return Collections2.filter(this.getAll(),
                new Predicate<Item>() {
                    @Override
                    public boolean apply(Item item) {
                        return item.hasOwner(user);
                    }
                }
        );
    }

    @Override
    protected PersistentItem transform(Item item) {
        PersistentItem persItem = new PersistentItem();
        persItem.description = item.description;
        persItem.picture = item.picture;
        persItem.ownerId = item.owner.id;
        persItem.id = item.id;

        return persItem;
    }

    @Override
    protected Item transform(PersistentItem persistent) {
        User user = this.userHome.get(persistent.ownerId);
        Item item = new Item(user, persistent.description, persistent.picture);
        item.id = persistent.id;
        return item;
    }

    @Override
    protected Class<PersistentItem> persistentType() {
        return PersistentItem.class;
    }
}

