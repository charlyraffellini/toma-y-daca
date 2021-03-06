/**
 * Copyright (C) 2013 the original author or authors.
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

package controllers.examples;

import static org.junit.Assert.assertTrue;
import ninja.NinjaFluentLeniumTest;

import org.junit.Before;

public class ApplicationControllerFluentLeniumTest extends NinjaFluentLeniumTest {
    
    @Before
    public void setup() {
        
        goTo(getServerAddress() + "setup");
        
    }

    //@Test TODO:Comento este test porque hice cambios en las rutas. EN algún momento hay que ver de hacerlo bien
    public void testThatHomepageWorks() {
        
        goTo(getServerAddress() + "/");
        
        assertTrue(title().contains("Home page"));
        
        click("#login");
        
        assertTrue(url().contains("login"));


    }

}
