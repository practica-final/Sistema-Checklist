package domainapp.modules.simple.dominio.reportes;

public class ReporteOperario {

    private String legajoSAP;
    private String nombreyApellido;
    private String email;
    private String numeroLicencia;
    private String vencimientoLicencia;

    public ReporteOperario(String legajoSAP, String nombreyApellido, String email, String numeroLicencia, String vencimientoLicencia){
        this.legajoSAP = legajoSAP;
        this.nombreyApellido = nombreyApellido;
        this.email = email;
        this.numeroLicencia = numeroLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public ReporteOperario(){}

    public String getlegajoSAP(){ return this.legajoSAP; }
    public String getnombreyApellido(){ return this.nombreyApellido; }
    public String getemail(){ return  this.email; }
    public String getnumeroLicencia(){ return this.numeroLicencia; }
    public String getvencimientoLicencia(){ return this.vencimientoLicencia; }
}

