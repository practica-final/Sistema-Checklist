package domainapp.modules.simple.dominio.reportes;

public class ReporteChecklist {

    private String identificacion;
    private String documentacion;
    private String tablero;
    private String laterales;
    private String seccionTrasera;
    private String frente;
    private String comentarios;

    public ReporteChecklist(String identificacion, String documentacion, String tablero, String laterales, String seccionTrasera,
                            String frente, String comentarios){
        this.identificacion = identificacion;
        this.documentacion = documentacion;
        this.tablero = tablero;
        this.laterales = laterales;
        this.seccionTrasera = seccionTrasera;
        this.frente = frente;
        this.comentarios = comentarios;
    }

    public ReporteChecklist(){}

    public String getIdentificacion(){ return this.identificacion; }
    public String getDocumentacion(){ return this.documentacion; }
    public String getTablero(){ return this.tablero; }
    public String getLaterales(){ return this.laterales; }
    public String getSeccionTrasera(){ return this.seccionTrasera; }
    public String getFrente(){ return this.frente; }
    public String getComentarios(){ return this.comentarios; }
}
