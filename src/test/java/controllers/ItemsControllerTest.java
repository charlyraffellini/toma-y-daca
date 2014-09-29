package controllers;

import dtos.ItemCreateDTO;
import homes.ItemHome;
import ninja.NinjaTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsControllerTest extends NinjaTest {

    @Test
    public void testPostAnItemReturnItsId() {
        int nextId = this.getInjector().getProvider(ItemHome.class).get().getNextId();

        String response = ninjaTestBrowser.postJson(getServerAddress()
                + "items", new ItemCreateDTO());

        assertEquals(String.valueOf(nextId), response);
    }
}
