package domainapp.modules.simple.dominio.vehiculo;

import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioEstado;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Vehiculo.class
)

public class VehiculoRepository {


    @Programmatic
    public List<Vehiculo> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Vehiculo.class,
                        "find"));
    }

    @Programmatic
    public Vehiculo findByDominio(final String dominio){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Vehiculo.class,
                        "findByDominio",
                        "dominio", dominio ));
    }

    @Programmatic
    public Vehiculo create(
            final String dominio, final String marca, final String modelo, final String anyo,
            final String kilometraje, final LocalDate vencimientoVtv, final LocalDate vencimientoPoliza,
            final EstadoVehiculo estado)
    {
        final Vehiculo vehiculo = new Vehiculo(dominio, marca, modelo, anyo, kilometraje,
                vencimientoVtv, vencimientoPoliza, estado);
        repositoryService.persist(vehiculo);
        return vehiculo;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}