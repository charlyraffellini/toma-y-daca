package controllers;

import com.google.gson.GsonBuilder;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Module;
import dtos.ItemCreateDTO;
import homes.ItemHome;
import ninja.NinjaTest;
import ninja.session.Session;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsControllerTest extends NinjaTest {

    @Test
    public void testPostAnItemReturnItsId() {
        int nextId = ninjaTestServer.getInjector().getProvider(ItemHome.class).get().getNextId();

        String response = ninjaTestBrowser.postJson(getServerAddress()
                + "items", new ItemCreateDTO());

        assertEquals(String.valueOf(nextId), response);
    }
}
