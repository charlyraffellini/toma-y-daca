package conf;

import com.google.inject.Provider;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import homes.PersistentItem;
import homes.PersistentUser;
import models.domain.User;

public class ObjectifyProvider implements Provider<Objectify> {
    
    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }



    static {
        ObjectifyService.register(PersistentUser.class);
//        ObjectifyService.register(User.class);
        ObjectifyService.register(PersistentItem.class);


//        ObjectifyService.register(TradeRequest.class);

        setup();
    }


    public static void setup() {


        Objectify ofy = ObjectifyService.ofy();

    }

}
