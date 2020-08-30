package domainapp.modules.simple.dominio.vehiculo;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.ObservadorGeneral;
import domainapp.modules.simple.dominio.operario.Operario;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.joda.time.LocalDate;

import javax.jdo.annotations.*;
import java.util.List;


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
public class Vehiculo implements Comparable<Vehiculo>, ObservadorGeneral {

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
    private EstadoGeneral estado;

    @Column(allowsNull = "false")
    @Property(hidden = Where.ALL_TABLES)
    private boolean bajaVehiculo;


    public String iconName() {
        if (this.estado == EstadoGeneral.Espera) {
            return "Espera";
        } else if (this.estado == EstadoGeneral.Habilitado) {
            return "Habilitado";
        } else if (this.estado == EstadoGeneral.Inhabilitado) {
            return "Inhabilitado";
        } else {
            return "Borrado";
        }
    }

    public String RepoDominio() {
        return this.dominio;
    }
    public String RepoMarca() {
        return this.marca;
    }
    public String RepoModelo() {
        return this.modelo;
    }
    public String RepoAnyo() {
        return this.anyo;
    }
    public String RepoKilometraje() {
        return this.kilometraje;
    }
    public String RepoVencimientoVtv() { return this.vencimientoVtv.toString("dd-MM-yyyy"); }
    public String RepoVencimientoPoliza() {
        return this.vencimientoPoliza.toString("dd-MM-yyyy");
    }
    public String RepoEstado() {
        return this.estado.toString();
    }

    public Vehiculo() { }

    public Vehiculo(
            final String dominio,
            final String marca,
            final String modelo,
            final String anyo,
            final String kilometraje,
            final LocalDate vencimientoVtv,
            final LocalDate vencimientoPoliza) {

        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anyo = anyo;
        this.kilometraje = kilometraje;
        this.vencimientoVtv = vencimientoVtv;
        this.vencimientoPoliza = vencimientoPoliza;
        this.estado = EstadoGeneral.Habilitado;
        this.bajaVehiculo = BajaVehiculo();
    }

    @NotPersistent
    @CollectionLayout(named = "Vehiculos en Espera")
    public List<Vehiculo> getEspera() {
        return vehiculoRepository.Listar(EstadoGeneral.Espera);
    }

    @NotPersistent
    @CollectionLayout(named = "Vehiculos Habilitados")
    public List<Vehiculo> getHabilitado() {
        return vehiculoRepository.Listar(EstadoGeneral.Habilitado);
    }

    @NotPersistent
    @CollectionLayout(named = "Vehiculos Inhabilitados")
    public List<Vehiculo> getInhabilitado() {
        return vehiculoRepository.Listar(EstadoGeneral.Inhabilitado);
    }

    @NotPersistent
    @CollectionLayout(named = "Vehiculos Borrados")
    public List<Vehiculo> getBorrado() {
        return vehiculoRepository.Listar(EstadoGeneral.Borrado);
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
            @ParameterLayout(named = "Vencimiento del seguro: ") final LocalDate vencimientoPoliza)

    {
        setDominio(dominio);
        setMarca(marca);
        setModelo(modelo);
        setAnyo(anyo);
        setKilometraje(kilometraje);
        setVencimientoVtv(vencimientoVtv);
        setVencimientoPoliza(vencimientoPoliza);
        return this;
    }

    public String default0Update() {return getDominio();}
    public String validate0Update(final String dominio) {
        return ValidarDominio(dominio);    }
    public String default1Update() {return getMarca();}
    public String default2Update() {return getModelo();}
    public String default3Update() {return getAnyo();}
    public String default4Update() {return getKilometraje();}
    public LocalDate default5Update() {return getVencimientoVtv();}
    public LocalDate default6Update() {return getVencimientoPoliza();}

    @Programmatic
    private String ValidarDominio(final String dominio) {
        if (Character.isDigit(dominio.charAt(0)) &&
                Character.isDigit(dominio.charAt(1)) &&
                (Character.compare(dominio.charAt(2), '-') == 0) &&
                Character.isDigit(dominio.charAt(3)) &&
                Character.isDigit(dominio.charAt(4)) &&
                Character.isDigit(dominio.charAt(5)) &&
                (Character.compare(dominio.charAt(6), '-') == 0) &&
                Character.isDigit(dominio.charAt(7)) &&
                Character.isDigit(dominio.charAt(8))) {
            return null;
        } else {
            return "Formato no valido XX-XXX-XX";
        }
    }

    @Programmatic
    public void CambiarEstado(EstadoGeneral estado){
        this.estado = estado;
    }

    @Programmatic
    public Vehiculo Espera(){
        CambiarEstado(EstadoGeneral.Espera);
        return this;
    }

    @Action()
    public Vehiculo Habilitar(){
        CambiarEstado(EstadoGeneral.Habilitado);
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Vehiculo Inhabilitar(){
        CambiarEstado(EstadoGeneral.Inhabilitado);
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Vehiculo Borrar(){
        CambiarEstado(EstadoGeneral.Borrado);
        return this;
    }

    public boolean hideHabilitar() {return this.estado == EstadoGeneral.Habilitado;}
    public boolean hideInhabilitar() {return this.estado == EstadoGeneral.Inhabilitado;}
    public boolean hideBorrar() {return this.estado == EstadoGeneral.Borrado;}

    @Override
    public void Actualizar (){

    }

    @Programmatic
    public boolean BajaVehiculo(){
        Actualizar();
        return this.bajaVehiculo;
    }

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


}