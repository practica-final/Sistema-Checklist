package domainapp.modules.simple.dominio.checklist;

import domainapp.modules.simple.dominio.reportes.Ejecutar;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "Checklist",
        repositoryFor = Checklist.class
)
@DomainServiceLayout(
        named = "Checklist",
        menuOrder = ""
)

public class ChecklistMenu {

    @Action()
    @ActionLayout(named = "Crear Checklist")
    @MemberOrder(sequence = "1")
    public Checklist create(

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Dominio: ")
            final Vehiculo vehiculo,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Identificacion")
            final String identificacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Destino")
            final String destino,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fecha de Salida")
            final LocalDate fechaSalida,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Documentacion")
            final EstadoChecklist documentacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tablero")
            final EstadoChecklist tablero,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Laterales")
            final EstadoChecklist laterales,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Seccion Trasera")
            final EstadoChecklist seccionTrasera,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Frente")
            final EstadoChecklist frente,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Comentarios")
            final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos")
            final Blob fotos)
    {
        return checklistRepository.create(vehiculo, identificacion, destino, fechaSalida, documentacion, tablero, laterales, seccionTrasera, frente, comentarios, fotos);
    }

    public List<Vehiculo> choices0Create() {return vehiculoRepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Checklist")
    @MemberOrder(sequence = "2")
    public Checklist findByVehiculo(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Vehiculo: ")
            final Vehiculo vehiculo) {
      TypesafeQuery<Checklist> q = isisJdoSupport.newTypesafeQuery(Checklist.class);
        final QChecklist cand = QChecklist.candidate();
        q = q.filter(
                cand.vehiculo.eq(q.stringParameter("vehiculo"))
        );
        return q.setParameter("vehiculo", vehiculo)
                .executeUnique();
    }

    public List<Vehiculo> choices0FindByVehiculo() {return vehiculoRepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "4")
    public List<Checklist> listAll() {
        List <Checklist> checklists =  checklistRepository.Listar();
        return checklists;
    }

    @Action()
    @ActionLayout(named = "Listado Exportado")
    public Blob ExportarListado() throws JRException, IOException {
        Ejecutar ejecutar = new Ejecutar();
        return ejecutar.ListadoChecklistPDF(checklistRepository.Listar());
    }

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    @javax.inject.Inject
    ChecklistRepository checklistRepository;

    @javax.inject.Inject
    VehiculoRepository vehiculoRepository;
}