package conf;

import com.google.inject.Provider;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import homes.PersistentItem;
import models.domain.Item;
import models.domain.User;

public class ObjectifyProvider implements Provider<Objectify> {
    
    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }



    static {
        ObjectifyService.register(User.class);
        ObjectifyService.register(PersistentItem.class);
//        ObjectifyService.register(TradeRequest.class);

        setup();
    }


    public static void setup() {

        String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit sed nisl sed lorem commodo elementum in a leo. Aliquam erat volutpat. Nulla libero odio, consectetur eget rutrum ac, varius vitae orci. Suspendisse facilisis tempus elit, facilisis ultricies massa condimentum in. Aenean id felis libero. Quisque nisl eros, accumsan eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula. eget ornare id, pharetra eget felis. Aenean purus erat, egestas nec scelerisque non, eleifend id ligula.";

        Objectify ofy = ObjectifyService.ofy();

        User user = ofy.load().type(User.class).first().now();

        //User bob = new User("123", "secret", "Bob");
        //bob.id = 1;
        //ofy.save().entity(bob).now();

        if (user == null) {

//            // Create a new user and save it
//            User bob = new User("123", "secret", "Bob");
//            ofy.save().entity(bob).now();


        }

    }

}
