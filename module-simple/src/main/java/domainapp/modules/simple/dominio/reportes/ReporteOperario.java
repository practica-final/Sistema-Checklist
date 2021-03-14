package domainapp.modules.simple.dominio.reportes;

public class ReporteOperario {

    private String legajoSAP;
    private String nombreyApellido;
    private String email;
    private String numeroLicencia;
    private String vencimientoLicencia;
    private String llaveRSV;
    private String estado;

    public ReporteOperario(String legajoSAP, String nombreyApellido, String email, String numeroLicencia, String vencimientoLicencia,
                           String llaveRSV, String estado){
        this.legajoSAP = legajoSAP;
        this.nombreyApellido = nombreyApellido;
        this.email = email;
        this.numeroLicencia = numeroLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
        this.llaveRSV = llaveRSV;
        this.estado = estado;
    }

    public ReporteOperario(){}

    public String getlegajoSAP(){ return this.legajoSAP; }
    public String getnombreyApellido(){ return this.nombreyApellido; }
    public String getemail(){ return  this.email; }
    public String getnumeroLicencia(){ return this.numeroLicencia; }
    public String getvencimientoLicencia(){ return this.vencimientoLicencia; }
    public String getLlaveRSV(){ return this.llaveRSV; }
    public String getEstado(){ return this.estado; }
}

