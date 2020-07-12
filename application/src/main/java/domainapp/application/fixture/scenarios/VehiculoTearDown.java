package domainapp.application.fixture.scenarios;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class VehiculoTearDown extends FixtureScript{

    @Override
    protected void execute(FixtureScript.ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"dominio\".\"vehiculo\"");
    }

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}

