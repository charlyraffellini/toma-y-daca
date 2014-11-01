package controllers.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import models.domain.User;
import org.junit.Test;

import com.googlecode.objectify.Objectify;
import conf.ObjectifyProvider;

public class UserTest extends NinjaAppengineBackendTest {


    public void createAndRetrieveUser() {
        
        ObjectifyProvider objectifyProvider = new ObjectifyProvider();
        Objectify ofy = objectifyProvider.get();
        
        
        // Create a new user and save it
        User user = new User("4321", "secret", "Bob");
        ofy.save().entity(user).now();
        

        // Retrieve the user with e-mail address bob@gmail.com
        User bob = ofy.load().type(User.class).filter("username", "bob@gmail.com").first().get();

        // Test
        assertNotNull(bob);
        assertEquals("Bob", bob.fullname);
    }

}
