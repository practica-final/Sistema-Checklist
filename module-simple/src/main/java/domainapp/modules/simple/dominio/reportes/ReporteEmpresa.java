package domainapp.modules.simple.dominio.reportes;

public class ReporteEmpresa {

    private String cuit;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String estado;

    public ReporteEmpresa(String cuit, String razonSocial, String direccion, String telefono, String estado){
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public ReporteEmpresa(){}

    public String getCuit(){ return this.cuit; }
    public String getRazonSocial(){ return this.razonSocial; }
    public String getDireccion(){ return  this.direccion; }
    public String getTelefono(){ return this.telefono; }
    public String getEstado(){ return this.estado; }
}

