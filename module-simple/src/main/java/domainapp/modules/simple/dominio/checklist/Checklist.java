package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.operario.OperarioRepository;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.title.TitleService;

import javax.jdo.annotations.*;


@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
})

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Checklist"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Unique(name = "Checklist_documentacion_UNQ", members = { "documentacion" })
@DomainObject(
        auditing = Auditing.ENABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter
@Setter
@lombok.RequiredArgsConstructor

public class Checklist implements Comparable<Checklist> {

    /*@Column(allowsNull = "false", length = 40)
    @Property()
    private String idChecklist;*/

    @lombok.NonNull
    @Column(allowsNull = "false")
    @Property()
    private Vehiculo vehiculo;


    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private EstadoChecklist documentacion;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private EstadoChecklist tablero;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private EstadoChecklist laterales;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private EstadoChecklist seccionTrasera;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private EstadoChecklist frente;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String comentarios;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String fotos;

//    public Checklist(Vehiculo vehiculo, EstadoChecklist documentacion, EstadoChecklist tablero, EstadoChecklist laterales, EstadoChecklist seccionTrasera, EstadoChecklist frente, String comentarios, String fotos) {
  //  }


    public Vehiculo title(){
        return getVehiculo();
    }

    //public Checklist(){ }

    /*public Checklist(
            /*String idChecklist, EstadoChecklist documentacion, EstadoChecklist tablero,
            EstadoChecklist laterales, EstadoChecklist seccionTrasera, EstadoChecklist frente,
            String comentarios, String fotos)
    {
       // this.idChecklist = idChecklist;
        this.documentacion = documentacion;
        this.tablero = tablero;
        this.laterales = laterales;
        this.seccionTrasera = seccionTrasera;
        this.frente = frente;
        this.comentarios = comentarios;
        this.fotos = fotos;
    }*/

    @Action()
    @ActionLayout(named = "Editar")
    public Checklist update(
            /*@Parameter(maxLength = 13)
            @ParameterLayout(named = "idChecklist: ") final String idChecklist,*/

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
       // setIdChecklist(idChecklist);
        setDocumentacion(documentacion);
        setTablero(tablero);
        setLaterales(laterales);
        setSeccionTrasera(seccionTrasera);
        setFrente(frente);
        setComentarios(comentarios);
        setFotos(fotos);
        return this;
    }

    //public String default0Update() {return getIdChecklist();}
    public EstadoChecklist default0Update() {return getDocumentacion();}
    public EstadoChecklist default1Update() {return getTablero();}
    public EstadoChecklist default2Update() {return getLaterales();}
    public EstadoChecklist default3Update() {return getSeccionTrasera();}
    public EstadoChecklist default4Update() {return getFrente();}
    public String default5Update() {return getComentarios();}
    public String default6Update(){return getFotos();}


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
    public String toString() {
        return getComentarios();
    }

    public int compareTo(final Checklist other) {
        return ComparisonChain.start()
                .compare(this.getComentarios(), other.getComentarios())
                .result();
    }

//    @Override
//    public String toString() {
//        return org.apache.isis.applib.util.ObjectContracts
//                .toString(this, "idChecklist");
//    }
    //endregion


    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    VehiculoRepository vehiculoRepository;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    OperarioRepository operarioRepository;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;

}