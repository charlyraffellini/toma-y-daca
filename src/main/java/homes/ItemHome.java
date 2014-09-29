package homes;

import com.google.common.base.Predicate;
import models.domain.Item;
import models.domain.User;
import com.google.common.collect.Collections2;

import java.util.Collection;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemHome extends Home<Item> {

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
}
