package models.integrations;

/**
 * Created by Palumbo on 27/09/2014.
 */
public class MeliApi {

    public Listing getListing(String listingId){
        return new Listing("Una descripcion", "Un link de foto");
    }
}
