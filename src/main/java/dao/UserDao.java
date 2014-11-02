package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.domain.User;

import com.googlecode.objectify.Objectify;

public class UserDao {

    @Inject
    Provider<Objectify> objectify;
    
    public boolean isUserAndPasswordValid(String username, String password) {
        
        if (username != null && password != null) {

            User user = objectify.get().load().type(User.class)
                    .filter("username", username).first().now();
            
            if (user != null) {
                

                
            }

        }
        
        return false;
 
    }

}
