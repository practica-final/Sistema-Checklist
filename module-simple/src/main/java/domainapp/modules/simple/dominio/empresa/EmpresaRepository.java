package domainapp.modules.simple.dominio.empresa;

import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.operario.Operario;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Empresa.class
)

public class EmpresaRepository {


    @Programmatic
    public List<Empresa> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Empresa.class,
                        "find"));
    }

    @Programmatic
    public Empresa findByCuit(final String cuit){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Empresa.class,
                        "findByCuit",
                        "cuit", cuit ));
    }

    @Programmatic
    public Empresa findByRazonSocial(final String razonSocial){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Empresa.class,
                        "findByRazonSocial",
                        "razonSocial", razonSocial ));
    }

    @Programmatic
    public Empresa create(
            final String razonSocial, final String direccion,
            final String cuit, final String telefono, final EstadoGeneral estado/*,
            final Operario operario*/)
    {
        final Empresa empresa = new Empresa(razonSocial, direccion, cuit, telefono, estado);
        repositoryService.persist(empresa);
        return empresa;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}