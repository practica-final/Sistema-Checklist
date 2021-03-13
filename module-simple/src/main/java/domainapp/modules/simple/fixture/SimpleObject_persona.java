/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package domainapp.modules.simple.fixture;

import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioMenu;
import org.apache.isis.applib.fixturescripts.PersonaWithBuilderScript;
import org.apache.isis.applib.fixturescripts.PersonaWithFinder;
import org.apache.isis.applib.fixturescripts.setup.PersonaEnumPersistAll;
import org.apache.isis.applib.services.registry.ServiceRegistry2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SimpleObject_persona implements PersonaWithBuilderScript<Operario, SimpleObjectBuilder>,
        PersonaWithFinder<Operario> {

    FOO("123456"),
    BAR("654321"),
    BAZ("246802"),
    FRODO("098765"),
    FROYO("567890"),
    FIZZ("357913"),
    BIP("123786"),
    BOP("987342"),
    BANG("876902"),
    BOO("412417");

    private final String legajoSAP;

//    @Override
    public SimpleObjectBuilder builder() {
        return new SimpleObjectBuilder().setLegajoSAP(legajoSAP);
    }

    //@Override
    public Operario findUsing(final ServiceRegistry2 serviceRegistry) {
        OperarioMenu simpleObjects = serviceRegistry.lookupService(OperarioMenu.class);
        return simpleObjects.findByLegajoSAP(legajoSAP);
    }

    public static class PersistAll
            extends PersonaEnumPersistAll<SimpleObject_persona, Operario, SimpleObjectBuilder> {
        public PersistAll() {
            super(SimpleObject_persona.class);
        }
    }
}
