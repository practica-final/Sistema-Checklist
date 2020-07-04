package domainapp.application.fixture.scenarios;

import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import net.sf.cglib.core.Local;

public class VehiculoCreate extends FixtureScript {

    private String dominio;
    private String marca;
    private String modelo;
    private String año;
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

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
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
        String año = checkParam("año", ec, String.class);
        String kilometraje = checkParam("kilometraje", ec, String.class);
        LocalDate vencimientoVtv = checkParam("vencimientoVtv", ec, LocalDate.class);
        LocalDate vencimientoPoliza = checkParam("vencimientoPoliza", ec, LocalDate.class);

        this.vehiculoObject = warp(menu).create(dominio, marca, modelo, año, kilometraje, vencimientoVtv, vencimientoPoliza);

        ec.addResult(this, vehiculoObject);
    }


    @javax.inject.Inject
    VehiculoMenu menu;


}
