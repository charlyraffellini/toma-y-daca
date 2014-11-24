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

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.Objectify;
import homes.Home;
import homes.ItemHome;
import homes.UserHome;
import models.domain.*;
import models.homes.TradeRequestHome;
import models.integrations.ListingsApi;
import ninja.appengine.AppEngineModule;
import ninja.session.Session;

import java.util.Arrays;
import java.util.List;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        bind(StartupActions.class);
        bind(Objectify.class).toProvider(ObjectifyProvider.class);

        this.bindHomes();
        this.bindApis();
        install(new AppEngineModule());        
//        bind(Session.class).to(EncryptedSession.class); TODO: descomentar para que quede encriptada la sesion
    }

    private void bindApis() {
        bind(ListingsApi.class);
    }

    private void bindHomes() {
        bind(UserHome.class).toInstance(new UserHome());
        bind(ItemHome.class).toInstance(new ItemHome());
        bind(TradeRequestHome.class).toInstance(new TradeRequestHome());
    }
}
