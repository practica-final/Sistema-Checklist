package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.operario.OperarioRepository;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.value.Blob;
import org.joda.time.LocalDate;


import javax.jdo.annotations.*;


@Getter
@Setter
@lombok.RequiredArgsConstructor

@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
})
@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "dominio",
        table = "Checklist"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@Unique(name = "Checklist_identificacion_UNQ", members = { "identificacion" })
@DomainObject(
        editing = Editing.DISABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)


public class Checklist {

    @lombok.NonNull
    @Property()
    @Column(allowsNull = "false")
    private Vehiculo vehiculo;

    @lombok.NonNull
    @Property()
    @Column(allowsNull = "false")
    private Operario operario;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String identificacion;

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String destino;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property()
    private LocalDate fechaSalida;

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

    @javax.jdo.annotations.Persistent(defaultFetchGroup="false", columns = {
            @javax.jdo.annotations.Column(name = "fotos_name"),
            @javax.jdo.annotations.Column(name = "fotos_mimetype"),
            @javax.jdo.annotations.Column(name = "fotos_bytes", jdbcType = "BLOB", sqlType = "VARBINARY")
    })
    @Property(optionality = Optionality.OPTIONAL)
    @lombok.NonNull
    @Getter @Setter
    private Blob fotos;


    public String title(){ return vehiculo.getDominio() + " " + getFechaSalida(); }

    public String ReporteIdentificacion(){ return this.identificacion; }
    public String ReporteDestino(){ return this.destino; }
    public LocalDate ReporteFechaSalida(){ return this.fechaSalida; }
    public String ReporteDocumentacion(){ return this.documentacion.toString(); }
    public String ReporteTablero(){ return this.tablero.toString(); }
    public String ReporteLaterales(){ return this.laterales.toString(); }
    public String ReporteSeccionTrasera(){ return this.seccionTrasera.toString(); }
    public String ReporteFrente(){ return this.frente.toString(); }
    public String ReporteComentarios(){ return this.comentarios; }


    /*
    @Action()
    @ActionLayout(named = "Editar")
    public Checklist update(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Identificacion")
            final String identificacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Destino")
            final String destino,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Documentacion")
            final EstadoChecklist documentacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tablero")
            final EstadoChecklist tablero,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Laterales")
            final EstadoChecklist laterales,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Seccion Trasera")
            final EstadoChecklist seccionTrasera,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Frente")
            final EstadoChecklist frente,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Comentarios")
            final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos")
            final Blob fotos){

        this.identificacion = identificacion;
        this.destino = destino;
        this.documentacion = documentacion;
        this.tablero = tablero;
        this.laterales = laterales;
        this.seccionTrasera = seccionTrasera;
        this.frente = frente;
        this.comentarios = comentarios;
        this.fotos = fotos;


        return this;

    }

    public String default0Update() {
        return getIdentificacion();
    }
    public String default1Update() { return getDestino(); }
    public EstadoChecklist default2Update() {
        return getDocumentacion();
    }
    public EstadoChecklist default3Update() {
        return getTablero();
    }
    public EstadoChecklist default4Update() {
        return getLaterales();
    }
    public EstadoChecklist default5Update() {
        return getSeccionTrasera();
    }
    public EstadoChecklist default6Update() {
        return getFrente();
    }
    public String default7Update() {
        return getComentarios();
    }
    public Blob default8Update() {
        return getFotos();
    }

*/


    @Override
    public String toString() {
        return getIdentificacion();
    }

    public int compareTo(final Checklist other) {
        return ComparisonChain.start()
                .compare(this.getIdentificacion(), other.getIdentificacion())
                .result();
    }

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    ChecklistRepository checklistRepository;

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