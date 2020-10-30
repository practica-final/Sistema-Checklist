package domainapp.modules.simple.dominio.operario;

/*import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;*/
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.empresa.EmpresaRepository;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import domainapp.modules.simple.dominio.operario.OperarioRepository;
import jdk.internal.dynalink.linker.ConversionComparator;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import org.apache.isis.applib.annotation.*;
import lombok.EqualsAndHashCode;
import org.apache.isis.applib.services.i18n.TranslatableString;

import javax.jdo.annotations.*;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Operario"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id"
)
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.VERSION_NUMBER, column = "version"
)
@javax.jdo.annotations.Unique(name = "Operario_legajoSAP_UNQ", members = {"legajoSAP"})
@DomainObject(
        auditing = Auditing.ENABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@lombok.Getter @lombok.Setter

public class Operario implements Comparable<Operario>
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
        private String llaveRSV;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private String clave;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private OperarioEstado estado;

        @Column(allowsNull = "true", name = "asig-empr")
        @Property()
        @PropertyLayout(named = "Empresa")
        private Empresa asigEmpresa;

        @Persistent(mappedBy = "operario", defaultFetchGroup = "true")
        @Column(allowsNull = "true")
        @Property()
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
        public String RepoNumeroLicencia(){ return this.numeroLicencia;}
        public String RepoVencimientoLicencia(){ return this.vencimientoLicencia;}
        public String RepoLlaveRSV(){ return this.llaveRSV;}
        public String RepoEmpresa(){ return this.asigEmpresa.getRazonSocial();}
        public String RepoEstado(){ return this.estado.toString();}

        public Operario(){}

        public Operario(
                final String nombreyApellido,
                final String legajoSAP,
                final String email,
                final String telefono,
                final String numeroLicencia,
                final String vencimientoLicencia,
                final String llaveRSV,
                final String clave
               // final Empresa asigEmpresa
                ){
            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.clave = clave;
            this.estado = OperarioEstado.Activo;
            //this.asigEmpresa = asigEmpresa;
        }

        public Operario(
                String nombreyApellido,
                String legajoSAP,
                String email,
                String telefono,
                String numeroLicencia,
                String vencimientoLicencia,
                String llaveRSV,
                String clave,
                Empresa empresa,
                OperarioEstado estado
               // List<Vehiculo> vehiculos
        ){
            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.clave = clave;
            this.asigEmpresa = empresa;
            this.estado = estado;
           // this.vehiculos = vehiculos;
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

       // public String getNombreyApellido() { return this.nombreyApellido;}

        @Action()
        @ActionLayout(named = "Editar")
        public Operario update(
                @Parameter(maxLength = 40)

                @ParameterLayout(named = "Nombre y Apellido: ")
                final String nombreyApellido,

                @ParameterLayout(named = "Legajo SAP: ")
                final String legajoSAP,

                @ParameterLayout(named = "Email: ")
                final String email,

                @ParameterLayout(named = "Telefono: ")
                final String telefono,

                @ParameterLayout(named = "Numero de Licencia: ")
                final String numeroLicencia,

                @ParameterLayout(named = "Vencimiento de Licencia: ")
                final String vencimientoLicencia,

                @ParameterLayout(named = "Llave RSV: ")
                final String llaveRSV,

                @ParameterLayout(named = "Clave: ")
                final String clave,

                @Parameter(optionality = Optionality.MANDATORY)
                @ParameterLayout(named = "Empresa: ")
                final Empresa asigEmpresa)

        {
            setNombreyApellido(nombreyApellido);
            setLegajoSAP(legajoSAP);
            setEmail(email);
            setTelefono(telefono);
            setNumeroLicencia(numeroLicencia);
            setVencimientoLicencia(vencimientoLicencia);
            setLlaveRSV(llaveRSV);
            setClave(clave);
            setAsigEmpresa(asigEmpresa);
            return this;
        }

        public String default0Update() { return getNombreyApellido();}
        public String default1Update() { return getLegajoSAP();}
        public String default2Update() { return getEmail();}
        public String default3Update() { return getTelefono();}
        public String default4Update() { return getNumeroLicencia();}
        public String default5Update() { return getVencimientoLicencia();}
        public String default6Update() { return getLlaveRSV();}
        public String default7Update() { return getClave();}
        /*public Empresa default8Update() {return getAsignarEmpresa();}
            public List<Empresa> choices8Update() {
                return empresaRepository.Listar(EstadoGeneral.Habilitado);
            }*/

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

        //@Override
        public void Notificar(){

           /*for ( Empresa empresa : empresa){
                empresa.Actualizar();
            }

            for (Vehiculo vehiculo : vehiculos){
                vehiculo.Actualizar();
            }*/
        }

        @Action()
        @ActionLayout(named = "Asignar Empresa")
        public Operario AgregarEmpresa(
                @Parameter(optionality = Optionality.MANDATORY)
                @ParameterLayout(named = "Empresa")
                final Empresa empresa) {

            this.asigEmpresa = empresa;
            return this;
        }

        public List<Empresa> choices0AgregarEmpresa() { return empresaRepository.Listar(); }

        @Override
        public int compareTo(final Operario other){
            return ComparisonChain.start()
                    .compare(this.getLegajoSAP(), other.getLegajoSAP())
                    .result();
        }

        @Override
        public String toString() { return org.apache.isis.applib.util.ObjectContracts
                .toString(this, "legajoSAP");
        }

        @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        EmpresaRepository empresaRepository;


        @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        VehiculoRepository vehiculoRepository;

        @javax.inject.Inject
        @javax.jdo.annotations.NotPersistent
        @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
        OperarioRepository operarioRepository;

}