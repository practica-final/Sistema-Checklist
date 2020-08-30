package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import domainapp.modules.simple.dominio.operario.Operario;

import org.joda.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import net.sf.cglib.core.Local;

public class VehiculoCreate extends FixtureScript {

    private String dominio;
    private String marca;
    private String modelo;
    private String anyo;
    private String kilometraje;
    private LocalDate vencimientoVtv;
    private LocalDate vencimientoPoliza;

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public LocalDate getVencimientoVtv() {
        return vencimientoVtv;
    }

    public void setVencimientoVtv(LocalDate vencimientoVtv) {
        this.vencimientoVtv = vencimientoVtv;
    }

    public LocalDate getVencimientoPoliza() {
        return vencimientoPoliza;
    }

    public void setVencimientoPoliza(LocalDate vencimientoPoliza) {
        this.vencimientoPoliza = vencimientoPoliza;
    }

    private Vehiculo vehiculoObject;

    public Vehiculo getVehiculoObject() {
        return vehiculoObject;
    }

    @Override
    protected void execute(final ExecutionContext ec) {

        String dominio = checkParam("dominio", ec, String.class);
        String marca = checkParam("marca", ec, String.class);
        String modelo = checkParam("modelo", ec, String.class);
        String anyo = checkParam("anyo", ec, String.class);
        String kilometraje = checkParam("kilometraje", ec, String.class);
        LocalDate vencimientoVtv = checkParam("vencimientoVtv", ec, LocalDate.class);
        LocalDate vencimientoPoliza = checkParam("vencimientoPoliza", ec, LocalDate.class);
        Operario operario = checkParam("operario", ec, Operario.class);


        this.vehiculoObject = wrap(menu).create(dominio, marca, modelo, anyo, kilometraje, vencimientoVtv, vencimientoPoliza, operario);

        ec.addResult(this, vehiculoObject);
    }


    @javax.inject.Inject
    VehiculoMenu menu;
}
