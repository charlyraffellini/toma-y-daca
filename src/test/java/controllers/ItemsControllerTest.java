package controllers;

import models.dtos.ItemCreateDTO;
import ninja.NinjaTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsControllerTest extends NinjaTest {

    @Before
    public void setup() {
        ninjaTestBrowser.makeRequest(getServerAddress() + "setup");
    }

    @Test
    public void testPostAnItemReturnItsId() {

        String response = ninjaTestBrowser.postJson(getServerAddress()
                + "items", new ItemCreateDTO());

        assertEquals("1", response);
    }
}
