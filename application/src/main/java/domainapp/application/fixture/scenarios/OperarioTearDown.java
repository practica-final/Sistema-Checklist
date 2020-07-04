package domainapp.application.fixture.scenarios;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class OperarioTearDown extends FixtureScript {

    @Override
    protected void execute(FixtureScript.ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"dominio\".\"operario\"");
    }

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}
