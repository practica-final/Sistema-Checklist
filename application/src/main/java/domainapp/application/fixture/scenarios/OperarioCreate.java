package domainapp.application.fixture.scenarios;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioMenu;


public class OperarioCreate extends FixtureScript {

    private String nombreyApellido;
    private String legajoSAP;
    private String empresa;
    private String email;
    private String telefono;
    private String numeroLicencia;
    private String vencimientoLicencia;
    private boolean llaveRSV;
    private String clave;

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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public boolean isllaveRSV() {
        return llaveRSV;
    }

    public void setllaveRSV(boolean llaveRSV) {
        this.llaveRSV = llaveRSV;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    private Operario operarioObject;

    public Operario getOperarioObject() {
        return operarioObject;
    }
    @Override
    protected void execute(final ExecutionContext ec){
        String nombreyApellido = checkParam("nombreyAPellido", ec, String.class);
        String legajoSAP = checkParam("legajoSAP", ec, String.class);
        String empresa = checkParam("empresa", ec, String.class);
        String email = checkParam("email", ec, String.class);
        String telefono = checkParam("telefono", ec, String.class);
        String numeroLicencia = checkParam("numeroLicencia", ec, String.class);
        String vencimientoLicencia = checkParam("vencimientoLicencia", ec, String.class);
        Boolean llaveRSV = checkParam("llaveRSV", ec, Boolean.class);
        String clave = checkParam("clave", ec, String.class);

        this.operarioObject = wrap(menu).create(nombreyApellido, legajoSAP, empresa, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, clave);

        ec.addResult(this, operarioObject);
    }
    @javax.inject.Inject
    OperarioMenu menu;
}
