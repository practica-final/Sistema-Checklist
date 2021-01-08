package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.value.Blob;


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

    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String identificacion;

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

    /*@javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property()
    private String fotos;*/

    @javax.jdo.annotations.Persistent(defaultFetchGroup="false", columns = {
            @javax.jdo.annotations.Column(name = "fotos_name"),
            @javax.jdo.annotations.Column(name = "fotos_mimetype"),
            @javax.jdo.annotations.Column(name = "fotos_bytes", jdbcType = "BLOB", sqlType = "VARBINARY")
    })
    @Property(optionality = Optionality.OPTIONAL)
    @lombok.NonNull
    @Getter @Setter
    private Blob fotos;


    public String title(){ return vehiculo.getDominio() + " " + vehiculo.getMarca(); }



    @Action()
    @ActionLayout(named = "Editar")
    public Checklist update(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Identificacion")
            final String identificacion,

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
    public EstadoChecklist default1Update() {
        return getDocumentacion();
    }
    public EstadoChecklist default2Update() {
        return getTablero();
    }
    public EstadoChecklist default3Update() {
        return getLaterales();
    }
    public EstadoChecklist default4Update() {
        return getSeccionTrasera();
    }
    public EstadoChecklist default5Update() {
        return getFrente();
    }
    public String default6Update() {
        return getComentarios();
    }
    public Blob default7Update() {
        return getFotos();
    }


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
    TitleService titleService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;
}