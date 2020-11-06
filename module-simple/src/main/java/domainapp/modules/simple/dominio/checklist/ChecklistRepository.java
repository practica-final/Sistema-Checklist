package domainapp.modules.simple.dominio.checklist;

import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Checklist.class
)

public class ChecklistRepository {


    @Programmatic
    public List<Checklist> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Checklist.class,
                        "find"));
    }

    @Programmatic
    public Vehiculo findByIdChecklist(final String idChecklist){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Vehiculo.class,
                        "findByIdChecklist",
                        "idChecklist", idChecklist ));
    }

    @Programmatic
    public Checklist create(
            final Vehiculo vehiculo, final EstadoChecklist documentacion, final EstadoChecklist tablero,
            final EstadoChecklist laterales, final EstadoChecklist seccionTrasera,
            final EstadoChecklist frente, final String comentarios, final String fotos)
    {
        final Checklist checklist = new Checklist(vehiculo, documentacion, tablero, laterales, seccionTrasera,
                frente, comentarios, fotos);
        repositoryService.persist(checklist);
        return checklist;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;

}