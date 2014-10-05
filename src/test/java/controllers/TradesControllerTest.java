package controllers;

import dtos.TradeCreateDTO;
import models.homes.TradeRequestHome;
import ninja.NinjaTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 29/09/2014.
 */
public class TradesControllerTest extends NinjaTest {

    @Test
    public void testPostAnItemReturnItsId() {
        int nextId = ninjaTestServer.getInjector().getProvider(TradeRequestHome.class).get().getNextId();

        TradeCreateDTO tradeDTO = new TradeCreateDTO();
        tradeDTO.friendId = 2;
        tradeDTO.friendItemId = 2;
        tradeDTO.userItemId = 1;

        String response = ninjaTestBrowser.postJson(getServerAddress()
                + "trade", tradeDTO);

        assertEquals(String.valueOf(nextId), response);
    }
}
