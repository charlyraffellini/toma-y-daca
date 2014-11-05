package controllers;

import dtos.ItemCreateDTO;
import homes.ItemHome;
import ninja.NinjaTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ItemsControllerTest extends NinjaTest {

    //@Test TODO: este test ya no anda porque no hay usuario logeado
    public void testPostAnItemReturnItsId() {
        int nextId = ninjaTestServer.getInjector().getProvider(ItemHome.class).get().getAll().size();

        ItemCreateDTO dto = new ItemCreateDTO();
        dto.meliId = "MLA524730552";

        String response = ninjaTestBrowser.postJson(getServerAddress()
                + "items", dto);

        assertEquals(String.valueOf(nextId), response);
    }
}
