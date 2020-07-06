package domainapp.modules.simple.dominio.operario;

import org.apache.isis.applib.annotation.*;

import java.io.IOException;
import org.apache.isis.applib.value.Blob;
import java.util.List;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "Operario",
        repositoryFor = Operario.class
)
@DomainServiceLayout(
        named = "",
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
            @ParameterLayout(named = "Empresa: ")
            final String empresa,

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
            final boolean llaveRSV,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Clave: ")
            final String clave)

        {
            return operarioRepository.create(nombreyApellido, legajoSAP, empresa, email, telefono, numeroLicencia, vencimientoLicencia,
                    llaveRSV, clave);
        }


        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Operario")
        @MemberOrder(sequence = "2")

        public Operario findByNombreyApellido(
                @Parameter(optionality = Optionality.MANDATORY)
                @ParameterLayout(named = "Buscar por Nombre: ")
                final Operario operario){

            return operario;
        }

        public List<Operario> choices0FindByNombreyApellido(){
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


}
