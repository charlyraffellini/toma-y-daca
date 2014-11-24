package models.integrations;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;
import homes.ItemHome;
import models.domain.Item;
import models.domain.TradeRequest;
import models.domain.User;

/**
 * Created by Federico on 23/11/14.
 */
public class FacebookAPI {
    public boolean postNewItem(User user, Item item) {
        String message = "Ha publicado un nuevo item: " + item.description;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource resource = client.resource("https://graph.facebook.com/v2.2/me/feed");

        Form form = new Form();
        form.add("message", message);
        form.add("link", item.picture);
        form.add("access_token", user.oauth_token);

        ClientResponse response = resource.accept("application/json").post(ClientResponse.class, form);

        return response.getStatus() == 200;
    }


    public boolean postTrade(TradeRequest trade) {
        Item item = trade.senderItem;
        String message = "Ha aceptado un cambio. Recibiste " + item.description;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource resource = client.resource("https://graph.facebook.com/v2.2/me/feed");

        Form form = new Form();
        form.add("message", message);
        form.add("link", item.picture);
        form.add("access_token", trade.receiverUser.oauth_token);

        ClientResponse response = resource.accept("application/json").post(ClientResponse.class, form);

        return response.getStatus() == 200;


    }

    public boolean sendNotification(TradeRequest trade) {

        User user = trade.receiverUser;
        Item item = trade.receiverItem;
        String message = user.fullname+ " ha aceptado tu cambio. Recibiste " + item.description;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource resource = client.resource("https://graph.facebook.com/v2.2/"+trade.senderUser.id+"/notifications");

        Form form = new Form();
        form.add("template", message);
        form.add("href", item.picture);
        form.add("access_token", trade.senderUser.oauth_token);

        ClientResponse response = resource.accept("application/json").post(ClientResponse.class, form);

        return response.getStatus() == 200;
    }
}
