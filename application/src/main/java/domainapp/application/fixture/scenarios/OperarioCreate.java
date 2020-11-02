package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.operario.OperarioEstado;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioMenu;

import org.joda.time.LocalDate;
//import java.time.LocalDate;


public class OperarioCreate extends FixtureScript {

    private String nombreyApellido;
    private String legajoSAP;
    private String email;
    private String telefono;
    private String numeroLicencia;
    private LocalDate vencimientoLicencia;
    private OperarioEstado llaveRSV;
    private OperarioEstado estado;


    public String getNombreyApellido() {
        return nombreyApellido;
    }
    public String getLegajoSAP() {
        return legajoSAP;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getNumeroLicencia() {
        return numeroLicencia;
    }
    public LocalDate getVencimientoLicencia() {
        return vencimientoLicencia;
    }
    public OperarioEstado getllaveRSV() {
        return llaveRSV;
    }
    public OperarioEstado getEstado() {
        return estado;
    }


    public void setNombreyApellido(String nombreyApellido) { this.nombreyApellido = nombreyApellido; }
    public void setLegajoSAP(String legajoSAP) { this.legajoSAP = legajoSAP; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setNumeroLicencia(String numeroLicencia) { this.numeroLicencia = numeroLicencia; }
    public void setVencimientoLicencia(LocalDate vencimientoLicencia) { this.vencimientoLicencia = vencimientoLicencia; }
    public void setllaveRSV(OperarioEstado llaveRSV) { this.llaveRSV = llaveRSV; }
    public void setEstado(OperarioEstado estado) { this.estado = estado; }

    private Operario operarioObject;

    public Operario getOperarioObject() {
        return operarioObject;
    }

    @Override
    protected void execute(final ExecutionContext ec){
        String nombreyApellido = checkParam("nombreyApellido", ec, String.class);
        String legajoSAP = checkParam("legajoSAP", ec, String.class);
        String email = checkParam("email", ec, String.class);
        String telefono = checkParam("telefono", ec, String.class);
        String numeroLicencia = checkParam("numeroLicencia", ec, String.class);
        LocalDate vencimientoLicencia = checkParam("vencimientoLicencia", ec, LocalDate.class);
        OperarioEstado llaveRSV = checkParam("llaveRSV", ec, OperarioEstado.class);
        OperarioEstado estado = checkParam("estado", ec, OperarioEstado.class);
        Empresa Empresa = checkParam("Empresa", ec, Empresa.class);

        this.operarioObject = wrap(menu).create(nombreyApellido, legajoSAP, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, estado);

        ec.addResult(this, operarioObject);
    }
    @javax.inject.Inject
    OperarioMenu menu;
}
