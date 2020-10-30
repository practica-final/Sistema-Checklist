package domainapp.modules.simple.dominio.operario;

import domainapp.modules.simple.dominio.empresa.Empresa;
import org.apache.isis.applib.annotation.*;

import java.io.IOException;
import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.List;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "Operario",
        repositoryFor = Operario.class
)
@DomainServiceLayout(
        named = "Operario",
        menuOrder = ""
)

public class OperarioMenu {


    @Action()
    @ActionLayout(named = "Crear Operario")
    @MemberOrder(sequence = "1")

    public Operario create(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre y Apellido: ")
            final String nombreyApellido,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Legajo SAP: ")
            final String legajoSAP,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Correo: ")
            final String email,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Telefono: ")
            final String telefono,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Número de Licencia: ")
            final String numeroLicencia,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Vencimiento de Licencia: ")
            final String vencimientoLicencia,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Llave RSV: ")
            final String llaveRSV,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Clave: ")
            final String clave,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Empresa: ")
            final Empresa asigEmpresa)

        {
            return operarioRepository.create(nombreyApellido, legajoSAP, email, telefono, numeroLicencia, vencimientoLicencia,
                    llaveRSV, clave, asigEmpresa);
        }


        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Operario")
        @MemberOrder(sequence = "2")

        public Operario findByLegajoSAP(
                @Parameter(optionality = Optionality.MANDATORY)
                @ParameterLayout(named = "Buscar por legajoSAP: ")
                final String legajoSAP){

            //return operario;

            TypesafeQuery<Operario> q = isisJdoSupport.newTypesafeQuery(Operario.class);
            final QOperario cand = QOperario.candidate();
            q = q.filter(
                    cand.legajoSAP.eq(q.stringParameter("legajoSAP"))
            );
            return q.setParameter("legajoSAP", legajoSAP)
                    .executeUnique();

        }

        public List<Operario> choices0FindByLegajoSAP(){
            return operarioRepository.Listar();
        }


        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Operarios")
        @MemberOrder(sequence = "3")
        public List<Operario> listAll(){
            List<Operario> operarios = operarioRepository.Listar();
            return operarios;
        }


        //@Action()
        //@ActionLayout(named = "Listado exportado de Operarios")
        //public Blob ExportarListado() throws JRException, IOException{
        //    EjectuarReportes ejectuarReportes = new EjecutarReportes();
        //    return ejectuarReportes.ListadoOperariosPDF(operarioRepository.Listar());
        //}


        @javax.inject.Inject
        OperarioRepository operarioRepository;

        @javax.inject.Inject
        IsisJdoSupport isisJdoSupport;
}
