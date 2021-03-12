package domainapp.modules.simple.dominio.vehiculo;

import com.google.common.collect.ComparisonChain;

import domainapp.modules.simple.dominio.checklist.Checklist;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.joda.time.LocalDate;

import javax.jdo.annotations.*;
import java.util.List;

@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
})

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Vehiculo"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Unique(name = "Vehiculo_dominio_UNQ", members = { "dominio" })
@DomainObject(
        auditing = Auditing.ENABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter
@Setter
public class Vehiculo implements Comparable<Vehiculo> {

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private String dominio;

    @Column(allowsNull = "false")
    @Property()
    private String marca;

    @Column(allowsNull = "false")
    @Property()
    private String modelo;

    @Column(allowsNull = "false")
    @Property()
    private String anyo;

    @Column(allowsNull = "false")
    @Property()
    private String kilometraje;

    @Column(allowsNull = "false")
    @Property()
    private LocalDate vencimientoVtv;

    @Column(allowsNull = "false")
    @Property()
    private LocalDate vencimientoPoliza;

    @Column(allowsNull = "false")
    @Property()
    private EstadoVehiculo estado;

    @Column(allowsNull = "true", name = "asig-operario")
    @Property()
    private Operario asignarOperario;

    @Persistent(mappedBy = "vehiculo", defaultFetchGroup = "true")
    @Column(allowsNull = "true")
    @Property()
    private List<Checklist> checklist;

    public String title(){
        return getDominio();
    }


    public String ReporteDominio(){ return this.dominio; }
    public String ReporteMarca(){ return this.marca; }
    public String ReporteModelo(){ return this.modelo; }
    public String ReporteAnyo(){ return this.anyo; }
    public String ReporteKilometraje() {return this.kilometraje; }
    public LocalDate ReporteVencimientoVtv() {return this.vencimientoVtv; }
    public LocalDate ReporteVencimientoPoliza() {return this.vencimientoPoliza; }
    public String ReporteEstado() {return this.estado.toString(); }

    public Vehiculo() { }

    public Vehiculo(
            String dominio, String marca, String modelo, String anyo,
            String kilometraje, LocalDate vencimientoVtv, LocalDate vencimientoPoliza,
            EstadoVehiculo estado) {

        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anyo = anyo;
        this.kilometraje = kilometraje;
        this.vencimientoVtv = vencimientoVtv;
        this.vencimientoPoliza = vencimientoPoliza;
        this.estado = estado;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Vehiculo update(
            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Dominio: ") final String dominio,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Marca: ") final String marca,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Modelo: ") final String modelo,

            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Año: ") final String anyo,

            @Parameter(maxLength = 80)
            @ParameterLayout(named = "Kilometraje: ") final String kilometraje,
            @ParameterLayout(named = "Vencimiento de la VTV: ") final LocalDate vencimientoVtv,
            @ParameterLayout(named = "Vencimiento del seguro: ") final LocalDate vencimientoPoliza,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Estado del vehículo: ") final EstadoVehiculo estado)

    {
        setDominio(dominio);
        setMarca(marca);
        setModelo(modelo);
        setAnyo(anyo);
        setKilometraje(kilometraje);
        setVencimientoVtv(vencimientoVtv);
        setVencimientoPoliza(vencimientoPoliza);
        setEstado(estado);
        return this;
    }

    public String default0Update() {return getDominio();}
    public String default1Update() {return getMarca();}
    public String default2Update() {return getModelo();}
    public String default3Update() {return getAnyo();}
    public String default4Update() {return getKilometraje();}
    public LocalDate default5Update() {return getVencimientoVtv();}
    public LocalDate default6Update() {return getVencimientoPoliza();}
    public EstadoVehiculo default7Update(){return getEstado();}


    @Programmatic
    public void CambiarEstado(EstadoVehiculo estado){
        this.estado = estado;
    }

    @Action()
    public Vehiculo Activo(){
        CambiarEstado(EstadoVehiculo.Activo);
        return this;
    }

    @Action()
    public Vehiculo NoActivo(){
        CambiarEstado(EstadoVehiculo.NoActivo);
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Vehiculo Baja(){
        CambiarEstado(EstadoVehiculo.Baja);
        return this;
    }

    @Action()
    @ActionLayout(named = "Asignar Operario")
    public Vehiculo AsignarOperario(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Operario")
            final Operario operario) {

        this.asignarOperario = operario;
        return this;
    }

    public List<Operario> choices0AsignarOperario() { return operarioRepository.Listar(); }



    //region > compareTo, toString
    @Override
    public int compareTo(final Vehiculo other) {
        //return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "dominio");
        return ComparisonChain.start()
                .compare(this.getDominio(), other.getDominio())
                .result();
    }
    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts
                .toString(this, "dominio");
    }
    //endregion


    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    VehiculoRepository vehiculoRepository;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    OperarioRepository operarioRepository;


}