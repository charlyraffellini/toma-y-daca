package conf;

import ninja.NinjaTest;
import ninja.session.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by charly on 9/14/14.
 */
public class EncryptedSessionTest extends NinjaTest {
    @Test
    public void testSessionMapDoesNotContainsDecryptedKey(){
        Session session = ninjaTestServer.getInjector().getInstance(Session.class);

        session.put("clave", "valor");
        assertFalse(session.getData().containsKey("clave"));
    }

    @Test
    public void testSessionCanReturnTheCorrectValue(){
        Session session = ninjaTestServer.getInjector().getInstance(Session.class);

        session.put("clave", "valor");

        assertEquals("valor", session.get("clave"));
    }
}