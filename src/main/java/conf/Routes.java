/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import com.google.inject.Inject;
import controllers.*;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router The default router of this application
     */
    @Override
    public void init(Router router)
    {
        
        // puts test data into db:
        if (!ninjaProperties.isProd()) {
            router.GET().route("/setup").with(ApplicationController.class, "setup");
        }

        ///////////////////////////////////////////////////////////////////////
        // Items
        ///////////////////////////////////////////////////////////////////////
        router.POST().route("/items").with(ItemsController.class, "createItem");
        router.GET().route("/items").with(ItemsController.class, "getAllItems");
        router.DELETE().route("/items/{itemId}").with(ItemsController.class, "deleteItem");

        ///////////////////////////////////////////////////////////////////////
        // Friends
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/friends").with(UserController.class, "getFriends");
        router.POST().route("/friends").with(UserController.class, "addFriend");
        router.GET().route("/friends/{friendId}/items").with(ItemsController.class, "getFriendItems");

        ///////////////////////////////////////////////////////////////////////
        // Trades
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/trades").with(TradesController.class, "listTradeRequests");
        router.POST().route("/trades").with(TradesController.class, "sendTradeRequest");
        router.PUT().route("/trades/{tradeId}").with(TradesController.class, "executeTradeRequest");


        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController.class, "login");
        router.POST().route("/login").with(LoginLogoutController.class, "loginPost");
        router.GET().route("/logout").with(LoginLogoutController.class, "logout");
        router.GET().route("/facelogin").with(LoginLogoutController.class, "faceLogin");
        router.GET().route("/face").with(LoginLogoutController.class, "faceReturn");

        ///////////////////////////////////////////////////////////////////////
        // Users
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/users").with(UserController.class,"getAllUsers");
        router.GET().route("/me").with(UserController.class,"getMe");

        ///////////////////////////////////////////////////////////////////////
        // Views
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/spa").with(MainController.class, "spa");
            router.GET().route("/searchItem").with(MainController.class, "searchItem");
                router.GET().route("/selectItem").with(MainController.class, "selectItem");
                router.GET().route("/defineItem").with(MainController.class, "defineItem");

            router.GET().route("/myItems").with(MainController.class, "myItems");
                router.GET().route("/listItems").with(MainController.class, "listItems");

        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/{fileName: .*}*").with(AssetsController.class, "serve");
        router.GET().route("/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");



        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController.class, "notFoundMessage");
        //.with(ApplicationController.class, "index");

    }

}
