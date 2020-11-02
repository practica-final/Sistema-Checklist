package domainapp.modules.simple.dominio.checklist;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType ="Checklist",
        repositoryFor = Checklist.class
)
@DomainServiceLayout(
        named = "Checklist",
        menuOrder =""
)

public class ChecklistMenu {

    @Action()
    @ActionLayout(named = "Crear Checklist")
    @MemberOrder(sequence = "1")
    public Checklist create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Id Checklist") final String idChecklist,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Documentación: ") final EstadoChecklist documentacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tablero: ") final EstadoChecklist tablero,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Laterales:  ") final EstadoChecklist laterales,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Sección Trasera:  ") final EstadoChecklist seccionTrasera,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Frente:  ") final EstadoChecklist frente,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Comentarios:  ") final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos:  ") final String fotos) {

        return checklistRepository.create(idChecklist, documentacion, tablero, laterales, seccionTrasera,
                frente, comentarios, fotos);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar por Checklist")
    @MemberOrder(sequence = "2")

    public Checklist findByIdChecklist(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Id Checklist: ")
            final String idChecklist) {

        TypesafeQuery<Checklist> q = isisJdoSupport.newTypesafeQuery(Checklist.class);
        final QChecklist cand = QChecklist.candidate();
        q = q.filter(
                cand.idChecklist.eq(q.stringParameter("idChecklist"))
        );
        return q.setParameter("idChecklist", idChecklist)
                .executeUnique();
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Checklist")
    @MemberOrder(sequence = "3")
    public List<Checklist> listAll() {
        return repositoryService.allInstances(Checklist.class);
    }

    @javax.inject.Inject
    RepositoryService repositoryService;


    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    @javax.inject.Inject
    ChecklistRepository checklistRepository;
}
