package controllers;

import com.google.inject.Singleton;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.appengine.AppEngineFilter;

/**
 * Created by charly on 11/4/14.
 */
@Singleton
@FilterWith(AppEngineFilter.class)
public class MeliListController {

    public Result showList(Context context) {

        return Results.html();

    }

}
