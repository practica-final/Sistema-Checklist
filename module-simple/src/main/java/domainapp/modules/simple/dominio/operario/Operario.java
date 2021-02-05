package domainapp.modules.simple.dominio.operario;


import com.google.common.collect.ComparisonChain;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.empresa.EmpresaRepository;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;


import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;

import net.sf.cglib.core.Local;
import org.joda.time.LocalDate;
//import java.time.LocalDate;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;

import java.util.List;
import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
})

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE, schema = "dominio", table = "Operario"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY, column = "id"
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
@Getter
@Setter

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

        @javax.jdo.annotations.Column(allowsNull = "true")
        @lombok.NonNull
        @Property() // editing disabled by default, see isis.properties
        @XmlJavaTypeAdapter(JodaDateTimeStringAdapter.ForJaxb.class)
        private LocalDate vencimientoLicencia;

        @Column(allowsNull = "false", length = 40)
        @Property()
        private OperarioEstado llaveRSV;


        @Column(allowsNull = "false", length = 40)
        @Property()
        private OperarioEstado estado;

        @Column(allowsNull = "true", name = "asig-empr")
        @Property()
        @PropertyLayout(named = "Empresa")
        private Empresa asigEmpresa;

        /*@Persistent(mappedBy = "asignarOperario", defaultFetchGroup = "true")*/
        @Column(allowsNull = "true")
        @Property()
        private Vehiculo vehiculo;

        public String title(){
            return getLegajoSAP() + " " + getNombreyApellido();
        }


        public String ReporteLegajoSAP() {return this.legajoSAP; }
        public String ReporteNombreyApellido() {return this.nombreyApellido; }
        public String ReporteEmail() {return this.email; }
        public String ReporteNumeroLicencia() {return this.numeroLicencia; }
        public LocalDate ReporteVencimientoLicencia() {return this.vencimientoLicencia; }


        public Operario(){}

        public Operario(
                String nombreyApellido, String legajoSAP, String email, String telefono, String numeroLicencia, LocalDate vencimientoLicencia, OperarioEstado llaveRSV, OperarioEstado estado){

            this.nombreyApellido = nombreyApellido;
            this.legajoSAP = legajoSAP;
            this.email = email;
            this.telefono = telefono;
            this.numeroLicencia = numeroLicencia;
            this.vencimientoLicencia = vencimientoLicencia;
            this.llaveRSV = llaveRSV;
            this.estado = estado;

        }

        @Action()
        @ActionLayout(named = "Editar")
        public Operario update(

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Nombre y Apellido: ")
                final String nombreyApellido,

                @Parameter(maxLength = 20)
                @ParameterLayout(named = "Legajo SAP: ")
                final String legajoSAP,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Email: ")
                final String email,

                @Parameter(maxLength = 40)
                @ParameterLayout(named = "Telefono: ")
                final String telefono,

                @Parameter(maxLength = 15)
                @ParameterLayout(named = "Numero de Licencia: ")
                final String numeroLicencia,

                @Parameter(maxLength = 80)
                @ParameterLayout(named = "Vencimiento de Licencia: ")
                final LocalDate vencimientoLicencia,

                @ParameterLayout(named = "Llave RSV: ")
                final OperarioEstado llaveRSV,

                @ParameterLayout(named = "Estado: ")
                final OperarioEstado estado)


        {
            setNombreyApellido(nombreyApellido);
            setLegajoSAP(legajoSAP);
            setEmail(email);
            setTelefono(telefono);
            setNumeroLicencia(numeroLicencia);
            setVencimientoLicencia(vencimientoLicencia);
            setLlaveRSV(llaveRSV);
            setEstado(estado);
            return this;
        }

        public String default0Update() { return getNombreyApellido();}
        public String default1Update() { return getLegajoSAP();}
        public String default2Update() { return getEmail();}
        public String default3Update() { return getTelefono();}
        public String default4Update() { return getNumeroLicencia();}
        public LocalDate default5Update() { return getVencimientoLicencia();}
        public OperarioEstado default6Update() { return getLlaveRSV();}
        public OperarioEstado default7Update() { return getEstado();}


        @Programmatic
        public void CambioEstado(OperarioEstado estado){
            this.estado = estado;

        }

        @Action()
        public Operario Activado(){
            CambioEstado(OperarioEstado.Activo);
            return this;
        }

        @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
        public Operario Desactivado(){
            CambioEstado(OperarioEstado.Inactivo);
            return this;
        }

        @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
        public Operario Eliminar(){
            CambioEstado(OperarioEstado.Eliminado);
            return this;
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