package models.integrations;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import models.domain.exceptions.BadMeliRequestException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class ListingsApi {

    public Listing getListing(String listingId){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource resource = client.resource("https://api.mercadolibre.com/items/" + listingId);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);

        try {
            return this.transform(new JSONObject(response.getEntity(String.class)));
        } catch (JSONException e) {
            throw new BadMeliRequestException(e);
        }

    }

    private Listing transform(JSONObject jsonObject) throws JSONException {
        return new Listing(
                jsonObject.getString("title"),
                jsonObject.getJSONArray("pictures").getJSONObject(0).getString("url")
        );
    }
}
