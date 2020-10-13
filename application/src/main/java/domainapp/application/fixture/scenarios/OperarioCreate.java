package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.empresa.Empresa;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioMenu;


public class OperarioCreate extends FixtureScript {

    private String nombreyApellido;
    private String legajoSAP;
    private String email;
    private String telefono;
    private String numeroLicencia;
    private String vencimientoLicencia;
    private String llaveRSV;
    private String clave;
    private Empresa empresa;

    public String getNombreyApellido() {
        return nombreyApellido;
    }
    public void setNombreyApellido(String nombreyApellido) {
        this.nombreyApellido = nombreyApellido;
    }

    public String getLegajoSAP() {
        return legajoSAP;
    }
    public void setLegajoSAP(String legajoSAP) {
        this.legajoSAP = legajoSAP;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }
    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }
    public void setVencimientoLicencia(String vencimientolicencia) {
        this.vencimientoLicencia = vencimientolicencia;
    }

    public String isllaveRSV() {
        return llaveRSV;
    }
    public void setllaveRSV(String llaveRSV) {
        this.llaveRSV = llaveRSV;
    }

    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(final Empresa empresa) { this.empresa = empresa; }

    private Operario operarioObject;
    public Operario getOperarioObject() {
        return operarioObject;
    }
    @Override
    protected void execute(final ExecutionContext ec){
        String nombreyApellido = checkParam("nombreyAPellido", ec, String.class);
        String legajoSAP = checkParam("legajoSAP", ec, String.class);
        String email = checkParam("email", ec, String.class);
        String telefono = checkParam("telefono", ec, String.class);
        String numeroLicencia = checkParam("numeroLicencia", ec, String.class);
        String vencimientoLicencia = checkParam("vencimientoLicencia", ec, String.class);
        String llaveRSV = checkParam("llaveRSV", ec, String.class);
        String clave = checkParam("clave", ec, String.class);
        Empresa empresa = checkParam("empresa", ec, Empresa.class);

        this.operarioObject = wrap(menu).create(nombreyApellido, legajoSAP, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, clave, empresa);

        ec.addResult(this, operarioObject);
    }
    @javax.inject.Inject
    OperarioMenu menu;
}
