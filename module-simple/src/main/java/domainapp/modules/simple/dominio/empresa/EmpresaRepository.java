package domainapp.modules.simple.dominio.empresa;

import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioEstado;
import domainapp.modules.simple.dominio.empresa.Empresa;
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
    public List <Empresa> Listar(Operario operario) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Empresa.class,
                        "findByOperario",
                        "operario", operario));
    }

    @Programmatic
    public List <Empresa> Listar (Operario operario, EstadoGeneral estado){
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Empresa.class,
                        "findByOperarioAndEstado",
                        "empresa", operario,
                        "estado", estado));
    }


    @Programmatic
    public List<Empresa> Listar (EstadoGeneral estado){
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Empresa.class,
                        "findByEstado",
                        "estado", estado ));
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
    public List<Empresa> findByCuitContains(final String cuit) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Empresa.class,
                        "findByCuitContains",
                        "cuit", cuit));
    }


    @Programmatic
    public Empresa create(
            final String razonSocial, final String direccion, final String cuit, final String telefono, final Operario operario)
    {
        final Empresa empresa = new Empresa(razonSocial, direccion, cuit, telefono);
        repositoryService.persist(empresa);
        return empresa;
    }

    @Programmatic
    public Empresa findOrCreate(
            final String razonSocial, final String direccion, final String cuit, final String telefono, final Operario operario)
    {
        Empresa empresa = findByCuit(cuit);
        if (empresa == null) {
            empresa = create(razonSocial, direccion, cuit, telefono, operario);
        }
        return empresa;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}