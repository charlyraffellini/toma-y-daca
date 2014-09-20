package controllers;

import ninja.NinjaTest;
import ninja.session.Session;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.IdentityHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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