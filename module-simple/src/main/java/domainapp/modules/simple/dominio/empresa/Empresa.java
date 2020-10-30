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

    /*public String iconName() {
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


    public String RepoRazonSocial() { return this.razonSocial; }
    public String RepoDireccion() { return this.direccion; }
    public String RepoCuit() { return this.cuit; }
    public String RepoTelefono() { return this.telefono; }
    public String RepoEstado() { return this.estado.toString(); }
    */


    public String title(){
        return getRazonSocial();
    }

    public Empresa() { }

    public Empresa(String razonSocial, String direccion, String cuit, String telefono, Operario operario) {

        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.cuit = cuit;
        this.telefono = telefono;
        this.operarios = (List<Operario>) operario;
    }

    /*public Empresa(
            final String razonSocial,
            final String direccion,
            final String cuit,
            final String telefono,
            List<Operario> operarios){

            this.razonSocial = razonSocial;
            this.direccion = direccion;
            this.cuit = cuit;
            this.telefono = telefono;
            this.operarios = operarios;
    }

    @NotPersistent
    @CollectionLayout(named = "Empresa en Espera")
    public List<Empresa> getEspera() {
        return empresaRepository.Listar(EstadoGeneral.Espera);
    }

    @NotPersistent
    @CollectionLayout(named = "Empresa Habilitada")
    public List<Empresa> getHabilitado() { return empresaRepository.Listar(EstadoGeneral.Habilitado);
    }

    @NotPersistent
    @CollectionLayout(named = "Empresa Inhabilitada")
    public List<Empresa> getInhabilitado() {
        return empresaRepository.Listar(EstadoGeneral.Inhabilitado);
    }

    @NotPersistent
    @CollectionLayout(named = "Empresa Borrada")
    public List<Empresa> getBorrado() {
        return empresaRepository.Listar(EstadoGeneral.Borrado);
    }*/


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
            @ParameterLayout(named = "Telefono: ") final String telefono)



    {
        setRazonSocial(razonSocial);
        setDireccion(direccion);
        setCuit(cuit);
        setTelefono(telefono);
        return this;
    }

    public String default0Update() {return getCuit();}
        /*public String validate0Update(final String cuit) {
        return ValidarCuit(cuit);    }*/
    public String default1Update() {return getRazonSocial();}
    public String default2Update() {return getDireccion();}
    public String default3Update() {return getTelefono();}



    /*@Programmatic
    private String ValidarCuit(final String cuit) {
        if (Character.isDigit(cuit.charAt(0)) &&
                Character.isDigit(cuit.charAt(1)) &&
                (Character.compare(cuit.charAt(2), '-') == 0) &&
                Character.isDigit(cuit.charAt(3)) &&
                Character.isDigit(cuit.charAt(4)) &&
                Character.isDigit(cuit.charAt(5)) &&
                Character.isDigit(cuit.charAt(6)) &&
                Character.isDigit(cuit.charAt(7)) &&
                Character.isDigit(cuit.charAt(8)) &&
                Character.isDigit(cuit.charAt(9)) &&
                Character.isDigit(cuit.charAt(10)) &&
                (Character.compare(cuit.charAt(11), '-') == 0) &&
                Character.isDigit(cuit.charAt(12))) {
            return null;
        } else {
            return "Formato no valido XX-XXXXXXXX-X";
        }
    }
    */

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

    /*@Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Empresa Borrar(){
        CambiarEstado(EstadoGeneral.Borrado);
        return this;
    }

    public boolean hideHabilitar() {return this.estado == EstadoGeneral.Habilitado;}
    public boolean hideInhabilitar() {return this.estado == EstadoGeneral.Inhabilitado;}
    public boolean hideBorrar() {return this.estado == EstadoGeneral.Borrado;}

    @Programmatic
    public EstadoGeneral ObtenerEstado(){
        return this.estado;
    }

     */


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
