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
import models.cosas_de_mas.FrutasBag;
import models.domain.*;
import models.homes.TradeRequestHome;
import models.integrations.MeliApi;
import ninja.appengine.AppEngineModule;
import ninja.session.Session;

import java.util.Arrays;
import java.util.List;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        bind(StartupActions.class);
        bind(Objectify.class).toProvider(ObjectifyProvider.class);
        bind(FrutasBag.class);
        this.bindHomes();
        this.bindApis();
        install(new AppEngineModule());        
        bind(Session.class).to(EncryptedSession.class);
    }

    private void bindApis() {
        bind(MeliApi.class);
    }

    private void bindHomes() {
        List<User> users = this.createUsers();
        List<Item> items = this.createItems(users);
        List<TradeRequest> trades = this.createTrades(users, items);

        bind(UserHome.class).toInstance(this.createHomeExample(new UserHome(), users));
        bind(ItemHome.class).toInstance(this.createHomeExample(new ItemHome(), items));
        bind(TradeRequestHome.class).toInstance(this.createHomeExample(new TradeRequestHome(), trades));
    }

    private List<User> createUsers() {
        User aUser = new User();
        User otherUser = new User();
        aUser.addFriend(otherUser);
        otherUser.addFriend(aUser);

        return Arrays.asList(aUser, otherUser);
    }

    private List<Item> createItems(List<User> users) {
        Item item1 = new Item(users.get(0), "Item 1", "asd");
        Item item2 = new Item(users.get(1), "Item 2", "dsa");

        return Arrays.asList(item1, item2);
    }

    private List<TradeRequest> createTrades(List<User> users, List<Item> items) {
        TradeRequest aTrade = users.get(1).sendTrade(items.get(1), users.get(0), items.get(0));
        return Arrays.asList(aTrade);
    }

    private <THome extends Home<TEntity>, TEntity extends DomainObject> THome createHomeExample(THome home, List<TEntity> entities) {
        for (TEntity entity : entities) {
            home.create(entity);
        }

        return home;
    }

}
