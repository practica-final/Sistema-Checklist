package domainapp.modules.simple.dominio.operario;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;

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
    public List<Operario> Listar(final OperarioEstado estado) {


        return repositoryService.allMatches(
                new QueryDefault<>(
                        Operario.class,
                        "findByEstado",
                        "estado", estado));
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
    public List<Operario> findByLegajoSAPContains(final String legajoSAP) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Operario.class,
                        "findByLegajoSAPContains",
                        "legajoSAP", legajoSAP));
    }


    @Programmatic
    public Operario create(
            final String nombreyApellido,
            final String legajoSAP,
            final String empresa,
            final String email,
            final String telefono,
            final String numeroLicencia,
            final String vencimientoLicencia,
            final Boolean llaveRSV,
            final String clave) {
        final Operario operario = new Operario(nombreyApellido, legajoSAP, empresa, email, telefono,
                numeroLicencia, vencimientoLicencia, llaveRSV, clave);
        repositoryService.persist(operario);
        return operario;
    }

    @Programmatic
    public Operario findOrCreate(
            final String nombreyApellido,
            final String legajoSAP,
            final String empresa,
            final String email,
            final String telefono,
            final String numeroLicencia,
            final String vencimientoLicencia,
            final Boolean llaveRSV,
            final String clave) {
        Operario operario = findByLegajoSAP(legajoSAP);
        if (operario == null) {
            operario = create(nombreyApellido, legajoSAP, empresa, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, clave);
        }
        return operario;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}