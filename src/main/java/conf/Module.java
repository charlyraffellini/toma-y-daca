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

import models.FrutasBag;
import models.homes.ItemHome;
import models.homes.UserHome;
import models.integrations.MeliApi;
import ninja.appengine.AppEngineModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.Objectify;
import ninja.session.Session;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        
        bind(StartupActions.class);
        bind(Objectify.class).toProvider(ObjectifyProvider.class);
        bind(FrutasBag.class);
        install(new AppEngineModule());        
        bind(Session.class).to(EncryptedSession.class);

        ///////////////////////////////////////////////////////////////////////
        // Apis
        ///////////////////////////////////////////////////////////////////////
        bind(MeliApi.class);

        ///////////////////////////////////////////////////////////////////////
        // Homes
        ///////////////////////////////////////////////////////////////////////
        bind(ItemHome.class);
        bind(UserHome.class);


    }

}
