package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import homes.UserHome;
import ninja.session.Session;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.appengine.AppEngineFilter;

@Singleton
@FilterWith(AppEngineFilter.class)
public class MainController extends WebApiController
{
    @Inject
    public MainController(UserHome userHome)
    {
        super(userHome);
    }

    /**
     *  Valida la existencia de la sesión de usuario
     *
     *  De existir la sesión, se renderiza el HTML correspondiente
     *   De no existir, redirige a /facelogin
     *
     *  @return Result
     */
    private Result getHtmlResult(Session session)
    {
        if(this.validateSessionExists(session)){
            return Results.redirect("/facelogin");
        }
        return Results.html();
    }

    public Result spa(Session session)
    {
        return this.getHtmlResult(session);
    }

    public Result searchItem(Session session)
    {
        return this.getHtmlResult(session);
    }

    public Result selectItem(Session session)
    {
        return this.getHtmlResult(session);
    }

    public Result defineItem(Session session)
    {
        return this.getHtmlResult(session);
    }

    public Result myItems(Session session) { return this.getHtmlResult(session); }

    public Result myFriends(Session session) { return this.getHtmlResult(session); }

    public Result myFriendsItems(Session session) { return this.getHtmlResult(session); }

    public Result myTrades(Session session) { return this.getHtmlResult(session); }

    public Result listItems(Session session)
    {
        return this.getHtmlResult(session);
    }
}
