package controllers.examples;

import ninja.NinjaTest;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.IdentityHashMap;

import static org.junit.Assert.assertFalse;

public class CookiesTest extends NinjaTest {

    @Before
    public void setup() {

        ninjaTestBrowser.makeRequest(getServerAddress() + "frutas");
    }

    @Test
    public void testCookies(){
        HttpResponse result = ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "frutas", new IdentityHashMap<String, String>());

        String value = ninjaTestBrowser.getCookieWithName("NINJA_SESSION").getValue();

        assertFalse(value.contains("caracteristica"));
    }
}