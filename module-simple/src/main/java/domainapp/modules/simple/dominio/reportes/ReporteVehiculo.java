package domainapp.modules.simple.dominio.reportes;

public class ReporteVehiculo {

        private String dominio;
        private String marca;
        private String modelo;
        private String anyo;
        private String kilometraje;
        private String vencimientoVtv;
        private String vencimientoPoliza;
        private String estado;

        public ReporteVehiculo(String dominio, String marca, String modelo, String anyo, String kilometraje, String vencimientoVtv,
                               String vencimientoPoliza, String estado){
            this.dominio = dominio;
            this.marca = marca;
            this.modelo = modelo;
            this.anyo = anyo;
            this.kilometraje = kilometraje;
            this.vencimientoVtv = vencimientoVtv;
            this.vencimientoPoliza = vencimientoPoliza;
            this.estado = estado;
        }

        public ReporteVehiculo(){}

        public String getDominio(){ return this.dominio; }
        public String getMarca(){ return this.marca; }
        public String getModelo(){ return  this.modelo; }
        public String getAnyo(){ return this.anyo; }
        public String getKilometraje(){ return this.kilometraje; }
        public String getVencimientoVtv(){ return this.vencimientoVtv; }
        public String getVencimientoPoliza(){ return this.vencimientoPoliza; }
        public String getEstado(){ return this.estado; }

    }
