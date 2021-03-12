package domainapp.modules.simple.dominio.checklist;

import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;
import org.joda.time.LocalDate;


import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Checklist.class)

public class ChecklistRepository {

    @Programmatic
    public List<Checklist> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Checklist.class,
                        "find"));
    }

    @Programmatic
    public Checklist buscarChecklist(final String identificacion){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Checklist.class,
                        "buscarChecklist",
                        "identificacion", identificacion));
    }

    @Programmatic
    public Checklist create(

            final Vehiculo vehiculo,
            final Operario operario,
            final String identificacion,
            final String destino,
            final LocalDate fechaSalida,
            final EstadoChecklist documentacion,
            final EstadoChecklist tablero,
            final EstadoChecklist laterales,
            final EstadoChecklist seccionTrasera,
            final EstadoChecklist frente,
            final String comentarios,
            final Blob fotos

    ) {
        final Checklist checklist = new Checklist(vehiculo, operario, identificacion, destino, fechaSalida,
                documentacion, tablero,
                laterales, seccionTrasera, frente, comentarios, fotos);
        repositoryService.persist(checklist);
        return checklist;
    }
    @javax.inject.Inject
    RepositoryService repositoryService;
}