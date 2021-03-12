package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.checklist.Checklist;
import domainapp.modules.simple.dominio.checklist.ChecklistMenu;
import domainapp.modules.simple.dominio.checklist.EstadoChecklist;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.value.Blob;
import org.joda.time.LocalDate;


public class ChecklistCreate extends FixtureScript {

    @Getter @Setter
    private Vehiculo vehiculo;

    @Getter @Setter
    private Operario operario;

    @Getter @Setter
    private String identificacion;

    @Getter @Setter
    private String destino;

    @Getter @Setter
    private LocalDate fechaSalida;

    @Getter @Setter
    private EstadoChecklist documentacion;

    @Getter @Setter
    private EstadoChecklist tablero;

    @Getter @Setter
    private EstadoChecklist laterales;

    @Getter @Setter
    private EstadoChecklist seccionTrasera;

    @Getter @Setter
    private EstadoChecklist frente;

    @Getter @Setter
    private String comentarios;

    @Getter @Setter
    private String fotos;

    @Getter @Setter
    private Checklist checklistObject;

    @Override
    protected void execute(final ExecutionContext ec) {

        Vehiculo vehiculo = checkParam("vehiculo", ec, Vehiculo.class);
        Operario operario = checkParam("operario", ec, Operario.class);
        String identificacion = checkParam("identificacion", ec, String.class);
        String destino = checkParam("destino", ec, String.class);
        LocalDate fechaSalida = checkParam("fechaSalida", ec, LocalDate.class);
        EstadoChecklist documentacion = checkParam("documentacion", ec, EstadoChecklist.class);
        EstadoChecklist tablero = checkParam("tablero", ec, EstadoChecklist.class);
        EstadoChecklist laterales = checkParam("laterales", ec, EstadoChecklist.class);
        EstadoChecklist seccionTrasera = checkParam("seccionTrasera", ec, EstadoChecklist.class);
        EstadoChecklist frente = checkParam("frente", ec, EstadoChecklist.class);
        String comentarios = checkParam("comentarios", ec, String.class);
        Blob fotos = checkParam("fotos", ec, Blob.class);

        this.checklistObject = wrap(menu).create(vehiculo, operario, identificacion, destino, fechaSalida,
                documentacion, tablero, laterales,
                seccionTrasera, frente, comentarios, fotos);

        // also make available to UI
        ec.addResult(this, checklistObject);
    }

    @javax.inject.Inject
    ChecklistMenu menu;
}