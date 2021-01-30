package domainapp.modules.simple.dominio.vehiculo;


import domainapp.modules.simple.dominio.reportes.Ejecutar;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;


import java.io.IOException;
import java.util.List;

import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;
import org.joda.time.LocalDate;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType ="Vehiculo",
        repositoryFor = Vehiculo.class
)
@DomainServiceLayout(
        named = "Vehiculo",
        menuOrder =""
)

public class VehiculoMenu {

    @Action()
    @ActionLayout(named = "Crear Vehiculo")
    @MemberOrder(sequence = "1")
    public Vehiculo create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Dominio (patente): ")
            final String dominio,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Marca: ")
            final String marca,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Modelo: ")
            final String modelo,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Año:  ")
            final String anyo,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Kilometraje: ")
            final String kilometraje,

            @ParameterLayout(named = "Vencimiento VTV: ")
            final LocalDate vencimientoVtv,

            @ParameterLayout(named = "Vencimiento Poliza: ")
            final LocalDate vencimientoPoliza,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Estado Vehiculo: ")
            final EstadoVehiculo estado/*,

            @ParameterLayout(named = "Operario: ")
            final Operario operario*/

            ) {

        return vehiculoRepository.create(dominio, marca, modelo,
                anyo, kilometraje, vencimientoVtv, vencimientoPoliza, estado);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Vehiculo")
    @MemberOrder(sequence = "2")
    public Vehiculo findByDominio(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Dominio: ")
            final String dominio) {
        TypesafeQuery<Vehiculo> q = isisJdoSupport.newTypesafeQuery(Vehiculo.class);
        final QVehiculo cand = QVehiculo.candidate();
        q = q.filter(
                cand.dominio.eq(q.stringParameter("dominio"))
        );
        return q.setParameter("dominio", dominio)
                .executeUnique();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named ="Listado de Vehiculos")
    @MemberOrder(sequence = "3")
    public List<Vehiculo> listAll() {
        return repositoryService.allInstances(Vehiculo.class);

    }

    @Action()
    @ActionLayout(named = "Listado Exportado")
    public Blob ExportarListado() throws JRException, IOException {
        Ejecutar ejecutar = new Ejecutar();
        return ejecutar.ListadoVehiculosPDF(vehiculoRepository.Listar());
    }

    @javax.inject.Inject
    RepositoryService repositoryService;


    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    @javax.inject.Inject
    VehiculoRepository vehiculoRepository;
}
