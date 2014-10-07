package integrations;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import models.integrations.Listing;
import models.integrations.ListingsApi;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Palumbo on 07/10/2014.
 */
public class ListingsApiTest {

    String listingId = "MLA524730552";
    JSONObject JSONListing;

    @Before
    public void initialize() throws JSONException {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource resource = client.resource("https://api.mercadolibre.com/items/" + this.listingId);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
        this.JSONListing = new JSONObject(response.getEntity(String.class));
    }

    @Test
    public void testMeli() throws JSONException {
        Listing listing = new ListingsApi().getListing(this.listingId);

        assertEquals(this.JSONListing.getString("title"), listing.description);
    }
}
