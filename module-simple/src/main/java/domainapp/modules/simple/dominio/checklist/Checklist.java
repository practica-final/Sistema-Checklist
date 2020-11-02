package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.operario.OperarioRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import javax.jdo.annotations.*;


@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Checklist"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Unique(name = "Checklist_idChecklist_UNQ", members = { "idChecklist" })
@DomainObject(
        auditing = Auditing.ENABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter
@Setter
public class Checklist implements Comparable<Checklist> {

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String idChecklist;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoChecklist documentacion;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoChecklist tablero;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoChecklist laterales;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoChecklist seccionTrasera;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private EstadoChecklist frente;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String comentarios;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String fotos;

    /*@Column(allowsNull = "true", name = "asig-vehiculo")
    @Property()
    private Vehiculo asignarVehiculo;*/

    public String title(){
        return getIdChecklist();
    }

    public Checklist(){ }

    public Checklist(
            String idChecklist, EstadoChecklist documentacion, EstadoChecklist tablero,
            EstadoChecklist laterales, EstadoChecklist seccionTrasera, EstadoChecklist frente,
            String comentarios, String fotos)
    {
        this.idChecklist = idChecklist;
        this.documentacion = documentacion;
        this.tablero = tablero;
        this.laterales = laterales;
        this.seccionTrasera = seccionTrasera;
        this.frente = frente;
        this.comentarios = comentarios;
        this.fotos = fotos;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Checklist update(
            @Parameter(maxLength = 13)
            @ParameterLayout(named = "idChecklist: ") final String idChecklist,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Documentacion: ") final EstadoChecklist documentacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tablero: ") final EstadoChecklist tablero,

            @Parameter(maxLength = 13)
            @ParameterLayout(named = "Laterales: ") final EstadoChecklist laterales,

            @Parameter(maxLength = 80)
            @ParameterLayout(named = "Seccion Trasera: ") final EstadoChecklist seccionTrasera,

            @Parameter(maxLength = 80)
            @ParameterLayout(named = "Frente: ") final EstadoChecklist frente,

            @Parameter(maxLength = 80)
            @ParameterLayout(named = "Comentarios ") final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos: ") final String fotos)
    {
        setIdChecklist(idChecklist);
        setDocumentacion(documentacion);
        setTablero(tablero);
        setLaterales(laterales);
        setSeccionTrasera(seccionTrasera);
        setFrente(frente);
        setComentarios(comentarios);
        setFotos(fotos);
        return this;
    }

    public String default0Update() {return getIdChecklist();}
    public EstadoChecklist default1Update() {return getDocumentacion();}
    public EstadoChecklist default2Update() {return getTablero();}
    public EstadoChecklist default3Update() {return getLaterales();}
    public EstadoChecklist default4Update() {return getSeccionTrasera();}
    public EstadoChecklist default5Update() {return getFrente();}
    public String default6Update() {return getComentarios();}
    public String default7Update(){return getFotos();}


    /*@Action()
    @ActionLayout(named = "Asignar Vehiculo")
    public Checklist AsignarVehiculo(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Vehiculo")
            final Vehiculo vehiculo) {

        this.asignarVehiculo = vehiculo;
        return this;
    }

    public List<Vehiculo> choices0AsignarVehiculo() { return vehiculoRepository.Listar(); }
*/

    @Override
    public int compareTo(final Checklist other) {
        return ComparisonChain.start()
                .compare(this.getIdChecklist(), other.getIdChecklist())
                .result();
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts
                .toString(this, "idChecklist");
    }
    //endregion


    /*@javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    VehiculoRepository vehiculoRepository;*/

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    OperarioRepository operarioRepository;

}