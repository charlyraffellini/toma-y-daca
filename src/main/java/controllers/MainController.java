package controllers;

import com.google.inject.Singleton;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.appengine.AppEngineFilter;

@Singleton
@FilterWith(AppEngineFilter.class)
public class MainController {

    public Result spa(Context context) {
        return Results.html();
    }

    public Result searchItem(Context context){
        return Results.html();
    }

    public Result selectItem(Context context){
        return Results.html();
    }
}
