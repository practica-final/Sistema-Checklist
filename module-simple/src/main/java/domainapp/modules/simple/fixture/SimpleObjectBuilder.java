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
import domainapp.modules.simple.dominio.operario.OperarioEstado;
import domainapp.modules.simple.dominio.operario.OperarioMenu;
import org.apache.isis.applib.fixturescripts.BuilderScriptAbstract;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joda.time.LocalDate;

@Accessors(chain = true)
public class SimpleObjectBuilder extends BuilderScriptAbstract<Operario, SimpleObjectBuilder> {

    @Getter @Setter
    private String nombreyApellido;

    @Getter @Setter
    private String legajoSAP;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String telefono;

    @Getter @Setter
    private String numeroLicencia;

    @Getter @Setter
    private LocalDate vencimientoLicencia;

    @Getter @Setter
    private OperarioEstado llaveRSV;

    @Getter @Setter
    private OperarioEstado estado;

    @Getter @Setter
    private Operario object;

    @Override
    protected void execute(final ExecutionContext ec) {

        checkParam("legajoSAP", ec, String.class);

        object = wrap(operarioMenu).create(nombreyApellido, legajoSAP, email,
                telefono, numeroLicencia, vencimientoLicencia, llaveRSV, estado);
    }

    @javax.inject.Inject
    OperarioMenu operarioMenu;

}
