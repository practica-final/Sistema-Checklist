package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.checklist.Checklist;
import domainapp.modules.simple.dominio.checklist.ChecklistMenu;
import domainapp.modules.simple.dominio.checklist.EstadoChecklist;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ChecklistCreate extends FixtureScript{

    private String idChecklist;
    private EstadoChecklist documentacion;
    private EstadoChecklist tablero;
    private EstadoChecklist laterales;
    private EstadoChecklist seccionTrasera;
    private EstadoChecklist frente;
    private String comentarios;
    private String fotos;

    public String getIdChecklist() {
        return idChecklist;
    }
    public EstadoChecklist getDocumentacion() {
        return documentacion;
    }
    public EstadoChecklist getTablero() {
        return tablero;
    }
    public EstadoChecklist getLaterales() {
        return laterales;
    }
    public EstadoChecklist getSeccionTrasera() {
        return seccionTrasera;
    }
    public EstadoChecklist getFrente() {
        return frente;
    }
    public String getComentarios() {
        return comentarios;
    }
    public String getFotos() {
        return fotos;
    }

    public void setIdChecklist (String idChecklist) {this.idChecklist = idChecklist;    }
    public void setDocumentacion(EstadoChecklist documentacion) { this.documentacion = documentacion; }
    public void setTablero (EstadoChecklist tablero) { this.tablero = tablero;}
    public void setLaterales (EstadoChecklist laterales) { this.laterales = laterales;}
    public void setSeccionTrasera (EstadoChecklist seccionTrasera) { this.seccionTrasera = seccionTrasera;}
    public void setFrente (EstadoChecklist frente) { this.frente = frente;}
    public void setComentarios (String comentarios) { this.comentarios = comentarios;}
    public void setFotos (String fotos) { this.fotos = fotos;}

    private Checklist checklistObject;

    public Checklist getChecklistObject() {
        return checklistObject;
    }

    @Override
    protected void execute(final ExecutionContext ec){
        String idChecklist = checkParam("idChecklist", ec, String.class);
        EstadoChecklist documentacion = checkParam("documentacion", ec, EstadoChecklist.class);
        EstadoChecklist tablero = checkParam("tablero", ec, EstadoChecklist.class);
        EstadoChecklist laterales = checkParam("laterales", ec, EstadoChecklist.class);
        EstadoChecklist seccionTrasera = checkParam("seccionTrasera", ec, EstadoChecklist.class);
        EstadoChecklist frente = checkParam("frente", ec, EstadoChecklist.class);
        String comentarios = checkParam("comentarios", ec, String.class);
        String fotos = checkParam("fotos", ec, String.class);
        Vehiculo vehiculo = checkParam("vehiculo", ec, Vehiculo.class);

        this.checklistObject = wrap(menu).create(idChecklist, documentacion, tablero, laterales, seccionTrasera,
                frente, comentarios,fotos);

        ec.addResult(this, checklistObject);
    }
    @javax.inject.Inject
    ChecklistMenu menu;
}

