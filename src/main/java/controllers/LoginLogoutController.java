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

package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import dao.UserDao;
import dtos.LoginDTO;
import homes.PersistentUser;
import homes.UserHome;
import models.domain.User;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.appengine.AppEngineFilter;
import ninja.params.Param;
import ninja.session.Session;
import ninja.utils.NinjaProperties;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Singleton
@FilterWith(AppEngineFilter.class)
public class LoginLogoutController {

    @Inject
    UserDao userDao;

    @Inject
    NinjaProperties ninjaProperties;

    @Inject
    UserHome userHome;
    private String redirectURI;


    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context)
    {

        return Results.html();

    }

    public Result loginPost(@Param("username") String username,
                            @Param("password") String password,
                            Context context)
    {

        boolean isUserNameAndPasswordValid = userDao.isUserAndPasswordValid(username, password);


        if (isUserNameAndPasswordValid) {
            context.getSessionCookie().put("username", username);
            context.getFlashCookie().success("login.loginSuccessful");

            return Results.redirect("/");

        } else {

            // something is wrong with the input or password not found.
            context.getFlashCookie().put("username", username);
            context.getFlashCookie().error("login.errorLogin");

            return Results.redirect("/login");

        }

    }

		@Inject
		Provider<HttpServletRequest> httpServletRequestProvider;
		@Inject
		Provider<HttpServletResponse> httpServletResponseProvider;


    /**
     * Start of oauth2 autentication
     *
     * Redirect to facebook login url with
     * mandatory query string parameters
     * For more information see: https://cwiki.apache.org/confluence/display/OLTU/OAuth+2.0+Client+Quickstart
     *
     * NOTA PARA QUIEN LEA:
     *
     * Para evitar separatismo y trabajar colaborativamente, armé una cuenta de Face Dev con un usuario
     *  que podemos usar TODOS (como tendría que haber sido desde un ppio).
     *  Me lo piden por algún medio y si les copa lo laburamos así.
     *  Espero podamos hacer lo mismo con GAE.
     *
     * @return Result
     */
    public Result faceLogin() {
        OAuthClientRequest request;
        try {

            request = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.FACEBOOK)
                     // Fede App
                    // .setClientId("868005159879263")

                    // Javier App
                    .setClientId("792253304175939")
                    .setRedirectURI(this.getRedirectURI())
                    .setScope("publish_actions")
                    .buildQueryMessage();
        } catch (OAuthSystemException e) {
            throw new RuntimeException(e.getMessage());
        }

        return Results.redirect(request.getLocationUri());
    }

    public Result faceReturn(Session session){

        OAuthAuthzResponse oar = null;
        String accessToken;
        Long expiresIn;
        String me;

        try {
            oar = OAuthAuthzResponse.oauthCodeAuthzResponse(httpServletRequestProvider.get());
            String code = oar.getCode();
            OAuthClientRequest request = OAuthClientRequest
                    .tokenProvider(OAuthProviderType.FACEBOOK)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    // Fede App
                    //.setClientId("868005159879263")
                    //.setClientSecret("11a46133e0fb96c203d1c61d64f589ac")

                    // Javier App
                    .setClientId("792253304175939")
                    .setClientSecret("4f5514458d7dbac21e6f66b10d7229be")
                    .setRedirectURI(this.getRedirectURI())
                    .setCode(code)
                    .buildQueryMessage();

            //create OAuth client that uses custom http client under the hood
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            //Facebook is not fully compatible with OAuth 2.0 draft 10, access token response is
            //application/x-www-form-urlencoded, not json encoded so we use dedicated response class for that
            //Custom response classes are an easy way to deal with oauth providers that introduce modifications to
            //OAuth 2.0 specification
            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);

            accessToken = oAuthResponse.getAccessToken();
            expiresIn = oAuthResponse.getExpiresIn();

            OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://graph.facebook.com/me")
                    .setAccessToken(accessToken).buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            me = resourceResponse.getBody();
            JSONObject json = new JSONObject(me);


            Objectify ofy = ObjectifyService.ofy();


            User user = new User();
            user.id = json.getLong("id");
            user.fullname = json.getString("first_name") + " " + json.getString("last_name");
            user.oauth_token=accessToken;


            if (ofy.load().type(PersistentUser.class).filter("id", user.id).list().isEmpty()) {
                userHome.update(user);
            }

            session.put("userId",String.valueOf(user.id));

            LoginDTO dto = new LoginDTO();
            dto.id = user.id;
            dto.name = user.fullname;
            dto.accessToken = user.oauth_token;

            return Results.redirect("/spa");


        } catch (OAuthProblemException e) {
            throw new RuntimeException(e.getMessage());
        } catch (OAuthSystemException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSessionCookie().clear();
        context.getFlashCookie().success("login.logoutSuccessful");

        return Results.redirect("/");

    }


    public String getRedirectURI() {
        if (ninjaProperties.isProd()){
            return "http://staging-toma-y-daca.appspot.com/face";
        }
        return "http://localhost:8080/face";
    }

}
