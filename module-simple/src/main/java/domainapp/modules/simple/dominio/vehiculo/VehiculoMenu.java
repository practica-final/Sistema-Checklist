package domainapp.modules.simple.dominio.vehiculo;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.value.Blob;

import java.io.IOException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import java.time.LocalDate;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType ="Vehiculo",
        repositoryFor = Vehiculo.class
)
@DomainServiceLayout(
        named = "Vehiculo",
        menuOrder =""
)

public class VehiculoMenu {

    @Action()
    @ActionLayout(named = "Crear Vehiculo")
    @MemberOrder(sequence = "1")
    public Vehiculo create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Dominio: ")
            final String dominio,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Marca: ")
            final String marca,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Modelo: ")
            final String modelo,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Año:  ")
            final String anyo,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Kilometraje: ")
            final String kilometraje,

            @ParameterLayout(named = "Vencimiento VTV: ")
            final LocalDate vencimientoVtv,

            @ParameterLayout(named = "Vencimiento Poliza: ")
            final LocalDate vencimientoPoliza

            ) {

        return vehiculoRepository.create(dominio, marca, modelo, anyo, kilometraje, vencimientoVtv, vencimientoPoliza, operario);
    }

    public String validate0Create (final String dominio) {
        return ValidarDominio(dominio);
    }


    @Programmatic
    private String ValidarDominio(final String dominio){
        if (Character.isDigit(dominio.charAt(0)) &&
                Character.isDigit(dominio.charAt(1)) &&
                (Character.compare(dominio.charAt(2),'-') == 0) &&
                Character.isDigit(dominio.charAt(3)) &&
                Character.isDigit(dominio.charAt(4)) &&
                Character.isDigit(dominio.charAt(5)) &&
                Character.isDigit(dominio.charAt(6)) &&
                Character.isDigit(dominio.charAt(7)) &&
                Character.isDigit(dominio.charAt(8)) &&
                Character.isDigit(dominio.charAt(9)) &&
                Character.isDigit(dominio.charAt(10)) &&
                (Character.compare(dominio.charAt(11),'-') == 0) &&
                Character.isDigit(dominio.charAt(12))){
            return null;
        }   else {
                return "Formato no valido";
        }

    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Vehiculo")
    @MemberOrder(sequence = "2")

    public Vehiculo findByDominio(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por nombre: ")
            final Vehiculo vehiculo)
    {
        return vehiculo;
    }

    public List<Vehiculo> choices0FindByDominio() { return vehiculoRepository.Listar();}


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Vehiculos")
    @MemberOrder(sequence = "2")
    public java.util.List<Vehiculo> listAll(){
        return vehiculoRepository.Listar();
    }

    /*
    @Action()
    @ActionLayout(named = "Listado exportado")
    public Blob ExportarListado() throws JRException, IOException {
        EjecutarReportes ejecutarReportes = new EjecutarReportes();
        return ejecutarReportes.ListadoVehiculosPDF(vehiculoRepository.Listar());
    }
    */

    @javax.inject.Inject
    VehiculoRepository vehiculoRepository;
}
