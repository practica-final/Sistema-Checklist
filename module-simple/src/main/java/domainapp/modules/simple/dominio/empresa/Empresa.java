package domainapp.modules.simple.dominio.empresa;

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


@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
})

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Empresa"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Unique(name = "Empresa_cuit_UNQ", members = { "cuit" })
@DomainObject(
        auditing = Auditing.ENABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter
@Setter
public class Empresa implements Comparable<Empresa> {

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String razonSocial;

    @Column(allowsNull = "false")
    @Property()
    private String direccion;

    @Column(allowsNull = "false")
    @Property()
    private String cuit;

    @Column(allowsNull = "false")
    @Property()
    private String telefono;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoGeneral estado;

    @Persistent(mappedBy = "asigEmpresa", defaultFetchGroup = "true")
    @Column(allowsNull = "true")
    @Property()
    private List<Operario> operarios;


    public String title(){
        return getRazonSocial();
    }

    public String ReporteCuit() {return this.cuit; }
    public String ReporteRazonSocial() {return this.razonSocial; }
    public String ReporteDireccion() {return this.direccion; }
    public String ReporteTelefono() {return this.telefono; }
    public String ReporteEstado() {return this.estado.toString(); }

    public Empresa() { }

    public Empresa(String razonSocial, String direccion, String cuit, String telefono, EstadoGeneral estado) {

        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.cuit = cuit;
        this.telefono = telefono;
        this.estado = estado;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Empresa update(
            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Razon Social: ") final String razonSocial,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion: ") final String direccion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Cuit: ") final String cuit,

            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Telefono: ") final String telefono,

            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Estado: ") final EstadoGeneral estado)

    {
        setRazonSocial(razonSocial);
        setDireccion(direccion);
        setCuit(cuit);
        setTelefono(telefono);
        setEstado(estado);
        return this;
    }

    public String default0Update() {return getCuit();}
    public String default1Update() {return getRazonSocial();}
    public String default2Update() {return getDireccion();}
    public String default3Update() {return getTelefono();}
    public EstadoGeneral default4Update(){return getEstado();}

    @Programmatic
    public void CambiarEstado(EstadoGeneral estado){
        this.estado = estado;
    }

    @Programmatic
    public Empresa Espera(){
        CambiarEstado(EstadoGeneral.Espera);
        return this;
    }

    @Action()
    public Empresa Habilitar(){
        CambiarEstado(EstadoGeneral.Habilitado);
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Empresa Inhabilitar(){
        CambiarEstado(EstadoGeneral.Inhabilitado);
        return this;
    }


    //region > compareTo, toString
    @Override
    public int compareTo(final Empresa other) {
        //return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "dominio");
        return ComparisonChain.start()
                .compare(this.getCuit(), other.getCuit())
                .result();
    }
    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts
                .toString(this, "cuit");
    }
    //endregion


    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    EmpresaRepository empresaRepository;


}
