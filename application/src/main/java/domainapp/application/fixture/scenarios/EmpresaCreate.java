package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.empresa.EmpresaMenu;
import domainapp.modules.simple.dominio.operario.Operario;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class EmpresaCreate extends FixtureScript {

    private String razonSocial;
    private String direccion;
    private String cuit;
    private String telefono;

    public String getRazonSocial() {
        return razonSocial;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getCuit() {
        return cuit;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setRazonSocial (String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public void setDireccion (String direccion) { this.direccion = direccion; }
    public void setCuit (String cuit) { this.cuit = cuit;}
    public void setTelefono (String telefono) { this.telefono = telefono;}

    private Empresa empresaObject;

    public Empresa getEmpresaObject() {
        return empresaObject;
    }

    @Override
    protected void execute(final ExecutionContext ec){
        String razonSocial = checkParam("razonSocial", ec, String.class);
        String direccion = checkParam("direccion", ec, String.class);
        String cuit = checkParam("cuit", ec, String.class);
        String telefono = checkParam("telefono", ec, String.class);
        Operario operario = checkParam("operario", ec, Operario.class);

        this.empresaObject = wrap(menu).create(razonSocial, direccion, cuit, telefono, operario);

        ec.addResult(this, empresaObject);
    }
    @javax.inject.Inject
    EmpresaMenu menu;
}
