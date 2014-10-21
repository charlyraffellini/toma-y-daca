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

import com.google.inject.Provider;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.appengine.AppEngineFilter;
import ninja.params.Param;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.UserDao;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Singleton
@FilterWith(AppEngineFilter.class)
public class LoginLogoutController {
    
    @Inject
    UserDao userDao;
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context) {

        return Results.html();

    }

    public Result loginPost(@Param("username") String username,
                            @Param("password") String password,
                            Context context) {

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
		 * <p>
		 * Redirect to facebook login url with
		 * mandatory query string parameters
		 * For more information see: https://cwiki.apache.org/confluence/display/OLTU/OAuth+2.0+Client+Quickstart
		 * <p>
		 *
		 * @return Result
		 */
		public Result faceLogin(){
				OAuthClientRequest request;
				try {
						request = OAuthClientRequest
							.authorizationProvider(OAuthProviderType.FACEBOOK)
							.setClientId("1479249045698079")
							.setRedirectURI("http://localhost:8080/face")
							.buildQueryMessage();
				} catch (OAuthSystemException e) {
						throw new RuntimeException(e.getMessage());
				}

					return Results.redirect(request.getLocationUri());
		}

		public Result faceReturn(){

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
					.setClientId("1479249045698079")
					.setClientSecret("61dfe815137e0ed8ed8133a71331347b")
					.setRedirectURI("http://localhost:8080/face")
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

			} catch (OAuthProblemException e) {
				throw new RuntimeException(e.getMessage());
			} catch (OAuthSystemException e) {
				throw new RuntimeException(e.getMessage());
			}

			Map<String, String> stuffs = new HashMap<String, String>();
			stuffs.put("access_token", accessToken);
			stuffs.put("me", me);

			return Results.json().render(stuffs);
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


}
