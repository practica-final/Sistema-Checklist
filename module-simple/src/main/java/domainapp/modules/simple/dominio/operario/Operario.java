package domainapp.modules.simple.dominio.operario;

import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import org.apache.isis.applib.annotation.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import org.apache.isis.applib.services.i18n.TranslatableString;

import javax.jdo.annotations.*;

@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "dominio",
        table = "Operario"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id"
)
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version"
)
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                    + "FROM domainapp.modules.simple.dominio.operario.Operario"
                    + "ORDER BY nombreyApellido ASC"
        ),
        @Query(
                name = "findByNombreyApellidoContains", language = "JDOQL",
                value = "SELECT"
                        + "FROM domainapp.modules.simple.dominio.operario.Operario"
                        + "WHERE nombreyApellido.indexOf(:nombreyApellido) >= 0"
        ),
        @Query(
                name = "findByEstado", language = "JDOQL",
                value = "SELECT"
                        + "FROM domain.app.modules.simple.dominio.operario.Operario"
                        + "WHERE estado == :estado"
                        + "ORDER BY nombreyApellido ASC"
        )
})
@Unique(name = "Operario_legajoSAP_UNQ", members = { "legajoSAP"})
@DomainObject(
        editing = Editing.DISABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter @Setter
public class Operario implements Comparable<Operario>//, SujetoGeneral
    {
        @Column(allowsNull = "false", length = 40)
        @Property()
        @Title()
        private String nombreyApellido;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String legajoSAP;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String empresa;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String email;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String telefono;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String numeroLicencia;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String vencimientoLicencia;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private boolean llaveRSV;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String clave;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private OperarioEstado estado;

        /*@Persistent(mappedBy = "operario", defaultFetchGroup = "true")
        @Column(allowsNull = "true")
        @Property()
        private List<Empresa> empresas;
         */

        @Persistent(mappedBy = "operario", defaultFetchGroup = "true")
        @Column(allowsNull = "true")
        @Property
        private List<Vehiculo> vehiculos;


        public String iconName(){
            if (this.estado == OperarioEstado.Activo){
                return "Activo";
            } else if (this.estado == OperarioEstado.Inactivo){
                return "Inactivo";
            } else{
                return "Eliminado";
            }
        }

        public String RepoNombreyApellido(){ return this.nombreyApellido;}
        public String RepoLegajoSAP(){ return this.legajoSAP;}
        public String RepoEmpresa(){ return this.empresa;}
        //public String RepoEmail(){ return this.email;}
        //public String RepoTelefono(){ return this.telefono;}
        public String RepoNumeroLicencia(){ return this.numeroLicencia;}
        public String RepoVencimientoLicencia(){ return this.vencimientoLicencia;}
        public boolean RepoLlaveRSV(){ return this.llaveRSV;}
        //public String RepoClave(){ return this.clave;}
        public String RepoEstado(){ return this.estado.toString();}

        public Operario(){}

        public Operario(
                String nombreyApellido,
                String legajoSAP,
                String empresa,
                String email,
                String telefono,
                String numeroLicencia,
                String vencimientoLicencia,
                boolean llaveRSV,
                String clave
        ){
            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.empresa = empresa;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.clave = clave;
            this.estado = OperarioEstado.Activo;
        }

        public Operario(
                String nombreyApellido,
                String legajoSAP,
                String empresa,
                String email,
                String telefono,
                String numeroLicencia,
                String vencimientoLicencia,
                boolean llaveRSV,
                String clave,
                OperarioEstado estado,
                //List<Empresa> empresas,
                List<Vehiculo> vehiculos
        ){
            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.empresa = empresa;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.clave = clave;
            this.estado = estado;
            //this.empresas = empresas;
            this.vehiculos = vehiculos;
        }

        @NotPersistent
        @CollectionLayout(named = "Operarios Activos")
        public List<Operario> getActivo() { return operarioRepository.Listar(OperarioEstado.Activo);}

        @NotPersistent
        @CollectionLayout(named = "Operarios Inactivos")
        public List<Operario> getInactivo(){ return operarioRepository.Listar(OperarioEstado.Inactivo);}

        @NotPersistent
        @CollectionLayout(named = "Operarios Eliminados")
        public List<Operario> getEliminado(){ return operarioRepository.Listar(OperarioEstado.Eliminado);}

        public String getNombreyApellido() { return this.nombreyApellido;}

        @Action()
        @ActionLayout(named = "Editar")
        public Operario update(
                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Nombre y Apellido: ")
                final String nombreyApellido,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Legajo SAP: ")
                final String legajoSAP,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Empresa: ")
                final String empresa,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Email: ")
                final String email,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Telefono: ")
                final String telefono,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Numero de Licencia: ")
                final String numeroLicencia,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Vencimiento de Licencia: ")
                final String vencimientoLicencia,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Llave RSV: ")
                final boolean llaveRSV,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Clave: ")
                final String clave

        ){
            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.empresa = empresa;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.clave = clave;
            return this;
        }

        public String default0Update() { return getNombreyApellido();}
        public String default1Update() { return getLegajoSAP();}
        public String default2Update() { return getEmpresa();}
        public String default3Update() { return getEmail();}
        public String default4Update() { return getTelefono();}
        public String default5Update() { return getNumeroLicencia();}
        public String default6Update() { return getVencimientoLicencia();}
        public boolean default7Update() { return isLlaveRSV();}
        public String default8Update() { return getClave();}

        @Programmatic
        public void CambioEstado(OperarioEstado estado){
            this.estado = estado;
            Notificar();
        }

        @Action()
        public Operario Activar(){
            CambioEstado(OperarioEstado.Activo);
            return this;
        }

        @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
        public Operario Desactivar(){
            CambioEstado(OperarioEstado.Inactivo);
            return this;
        }

        @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
        public Operario Eliminar(){
            CambioEstado(OperarioEstado.Eliminado);
            return this;
        }

        public boolean hideActivar() { return this.estado == OperarioEstado.Activo;}
        public boolean hideDesactivar() { return this.estado == OperarioEstado.Inactivo;}
        public boolean hideEliminar() { return this.estado == OperarioEstado.Eliminado;}

        @Override
        public void Notificar(){

            /*for ( Empresa empresa : empresas){
                empresa.Actualizar();
            }
             */
            for (Vehiculo vehiculo : vehiculos){
                vehiculo.Actualizar();
            }
        }

        @Programmatic
        public OperarioEstado ObtenerEstado() { return this.estado;}

        @Override
        public int compareTo(final Operario other){
            return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "nombreyApellido");
        }

        @Override
        public String toString() { return org.apache.isis.applib.util.ObjectContracts.toString(this, "nombreyApellido");
        }

        /* @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        EmpresaRepository empresaRepository;
         */

        @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        VehiculoRepository vehiculoRepository;

        @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        OperarioRepository operarioRepository;

}