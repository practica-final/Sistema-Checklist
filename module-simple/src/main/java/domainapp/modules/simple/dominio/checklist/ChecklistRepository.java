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
    public List <Checklist> Listar(Vehiculo vehiculo) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Checklist.class,
                        "findByVehiculo",
                        "vehiculo", vehiculo));
    }

    @Programmatic
    public Checklist findByIdChecklist(final String idChecklist){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Checklist.class,
                        "findByIdChecklist",
                        "idChecklist", idChecklist ));
    }

    @Programmatic
    public List<Checklist> findByIdChecklistContains(final int idChecklist) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Checklist.class,
                        "findByIdChecklistContains",
                        "idChecklist", idChecklist));
    }

    @Programmatic
    public Checklist create(
            final String idChecklist, boolean documentacion, boolean tablero, boolean laterales, boolean seccionTrasera, boolean frente,
            String comentarios, String fotos, Vehiculo vehiculo)
    {
        final Checklist checklist = new Checklist(idChecklist, documentacion, tablero, laterales, seccionTrasera, frente, comentarios, fotos, vehiculo);
        repositoryService.persist(checklist);
        return checklist;
    }

    @Programmatic
    public Checklist findOrCreate(
            final String idChecklist, boolean documentacion, boolean tablero, boolean laterales, boolean seccionTrasera, boolean frente,
            String comentarios, String fotos, Vehiculo vehiculo)
    {
        Checklist checklist = findByIdChecklist(idChecklist);
        if (checklist == null) {
            checklist = create(idChecklist, documentacion, tablero, laterales, seccionTrasera, frente, comentarios, fotos, vehiculo);
        }
        return checklist;
    }


    @javax.inject.Inject
    RepositoryService repositoryService;
}
