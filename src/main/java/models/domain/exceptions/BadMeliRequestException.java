package models.domain.exceptions;

import org.codehaus.jettison.json.JSONException;

/**
 * Created by Palumbo on 07/10/2014.
 */
public class BadMeliRequestException extends RuntimeException {
    private JSONException exception;

    public BadMeliRequestException(JSONException e) {
        this.exception = e;
    }
}
