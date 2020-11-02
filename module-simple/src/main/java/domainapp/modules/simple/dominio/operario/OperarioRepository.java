package domainapp.modules.simple.dominio.operario;

import org.joda.time.LocalDate;
import java.util.List;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;



@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Operario.class
)

public class OperarioRepository {

    @Programmatic
    public List<Operario> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Operario.class,
                        "find"));
    }

    @Programmatic
    public Operario findByLegajoSAP(final String legajoSAP){

        return repositoryService.uniqueMatch(
            new QueryDefault<>(
                    Operario.class,
                    "findByLegajoSAP",
                    "legajoSAP", legajoSAP
            )
        );
    }

    @Programmatic
    public Operario create(
            final String nombreyApellido,
            final String legajoSAP,
            final String email,
            final String telefono,
            final String numeroLicencia,
            final LocalDate vencimientoLicencia,
            final OperarioEstado llaveRSV,
            final OperarioEstado estado)
    {
        final Operario operario = new Operario(nombreyApellido, legajoSAP, email, telefono,
                numeroLicencia, vencimientoLicencia, llaveRSV, estado);
        repositoryService.persist(operario);
        return operario;
    }

    @javax.inject.Inject
    RepositoryService repositoryService;

}