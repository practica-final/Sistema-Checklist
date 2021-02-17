package domainapp.modules.simple.dominio.operario;



import domainapp.modules.simple.dominio.reportes.Ejecutar;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;


import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;

import org.joda.time.LocalDate;
//import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;

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

            @Parameter(maxLength = 40,
                    regexPattern = "^[0-9]{9}$",
                    regexPatternReplacement = "Unicamente carga de números (9 como maximo)")
            @ParameterLayout(named = "Legajo SAP: ")
            final String legajoSAP,

            @Parameter(maxLength = 40,
                    regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
                    regexPatternFlags= Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Ingrese un correo valido (debe contener '@')")
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
            final LocalDate vencimientoLicencia,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Llave RSV: ")
            final OperarioEstado llaveRSV,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Estado: ")
            final OperarioEstado estado)

        {
            return operarioRepository.create(nombreyApellido, legajoSAP, email, telefono, numeroLicencia, vencimientoLicencia, llaveRSV, estado);
        }


        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Operario")
        @MemberOrder(sequence = "2")

        public Operario findByLegajoSAP(
                @Parameter(optionality = Optionality.MANDATORY)
                @ParameterLayout(named = "Buscar por legajoSAP: ")
                final String legajoSAP){
            TypesafeQuery<Operario> q = isisJdoSupport.newTypesafeQuery(Operario.class);
            final QOperario cand = QOperario.candidate();
            q = q.filter(
                    cand.legajoSAP.eq(q.stringParameter("legajoSAP"))
            );
            return q.setParameter("legajoSAP", legajoSAP)
                    .executeUnique();

        }

        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Operarios")
        @MemberOrder(sequence = "3")
        public List<Operario> listAll() {
            return repositoryService.allInstances(Operario.class);
        }


        @Action()
        @ActionLayout(named = "Listado Exportado")
        public Blob ExportarListado() throws JRException, IOException {
            Ejecutar ejecutar = new Ejecutar();
            return ejecutar.ListadoOperariosPDF(operarioRepository.Listar());
        }

        @javax.inject.Inject
        RepositoryService repositoryService;

        @javax.inject.Inject
        OperarioRepository operarioRepository;

        @javax.inject.Inject
        IsisJdoSupport isisJdoSupport;
}
