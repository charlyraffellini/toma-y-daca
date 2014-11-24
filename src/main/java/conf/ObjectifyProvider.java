package conf;

import com.google.inject.Provider;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import homes.PersistentItem;
import homes.PersistentTrade;
import homes.PersistentUser;

public class ObjectifyProvider implements Provider<Objectify> {
    
    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }



    static {
        ObjectifyService.register(PersistentUser.class);
        ObjectifyService.register(PersistentItem.class);
        ObjectifyService.register(PersistentTrade.class);

        setup();
    }


    public static void setup() {


        Objectify ofy = ObjectifyService.ofy();

    }

}
