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
    public Operario findByNombreyApellido(final String nombreyApellido){

        return repositoryService.uniqueMatch(
            new QueryDefault<>(
                    Operario.class,
                    "findByNombreyApellido",
                    "nombreyApellido", nombreyApellido
            )
        );
    }

    @Programmatic
    public List<Operario> findByNombreyApellidoContains(final String nombreyApellido) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Operario.class,
                        "findByNombreyApellidoContains",
                        "nombreyApellido", nombreyApellido));
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
            final String clave,
            final String confirmacion) {
        final Operario operario = new Operario(nombreyApellido, legajoSAP, empresa, email, telefono,
                numeroLicencia, vencimientoLicencia, llaveRSV, clave, confirmacion);
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
            final String clave,
            final String confirmacion) {
        Operario operario = findByNombreyApellido(nombreyApellido);
        if (operario == null) {
            operario = create(nombreyApellido, legajoSAP, empresa, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, clave, confirmacion);
        }
        return operario;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}

