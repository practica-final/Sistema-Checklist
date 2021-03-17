package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.EstadoGeneral;
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

    @javax.jdo.annotations.Column(allowsNull = "true", name = "estado")
    @Property()
    private EstadoGeneral estado;


    public String title(){ return vehiculo.getDominio() + " " + getFechaSalida(); }

    public String iconName(){
        if (this.estado == EstadoGeneral.Habilitado){
            return "Habilitado";
        }else{
            return "Inhabilitado";
        }
    }

    public String ReporteIdentificacion(){ return this.identificacion; }
    public String ReporteDestino(){ return this.destino; }
    public LocalDate ReporteFechaSalida(){ return this.fechaSalida; }
    public String ReporteDocumentacion(){ return this.documentacion.toString(); }
    public String ReporteTablero(){ return this.tablero.toString(); }
    public String ReporteLaterales(){ return this.laterales.toString(); }
    public String ReporteSeccionTrasera(){ return this.seccionTrasera.toString(); }
    public String ReporteFrente(){ return this.frente.toString(); }
    public String ReporteComentarios(){ return this.comentarios; }


    @Action()
    @ActionLayout(named = "Editar")
    public Checklist update(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Comentarios")
            final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos")
            final Blob fotos){

        this.comentarios = comentarios;
        this.fotos = fotos;


        return this;

    }

    public String default0Update() {
        return getComentarios();
    }
    public Blob default1Update() {
        return getFotos();
    }


    @Programmatic
    public void CambiarEstado(EstadoGeneral estado){
        this.estado = estado;
    }

    @Action()
    @ActionLayout(named = "Habilitar")
    public Checklist Habilitado(){
        CambiarEstado(EstadoGeneral.Habilitado);
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(named = "Inhabilitar")
    public Checklist Inhabilitado(){
        CambiarEstado(EstadoGeneral.Inhabilitado);
        return this;
    }
    public boolean hideHabilitado(){return  this.estado == EstadoGeneral.Habilitado;}
    public boolean hideInhabilitado(){return  this.estado == EstadoGeneral.Inhabilitado;}

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