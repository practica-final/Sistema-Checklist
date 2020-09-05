package domainapp.modules.simple.dominio.empresa;

import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.operario.QOperario;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.empresa.EmpresaRepository;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.value.Blob;

import java.io.IOException;
import java.util.List;
import org.joda.time.LocalDate;

import net.sf.jasperreports.engine.JRException;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType ="Empresa",
        repositoryFor = Empresa.class
)
@DomainServiceLayout(
        named = "Empresa",
        menuOrder =""
)

public class EmpresaMenu {

    @Action()
    @ActionLayout(named = "Crear Empresa")
    @MemberOrder(sequence = "1")
    public Empresa create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Razon Social: ")
            final String razonSocial,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion: ")
            final String direccion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Cuit: ")
            final String cuit,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Telefono:  ")
            final String telefono,

            @ParameterLayout(named = "Operario: ")
            final Operario operario

    ) {

        return empresaRepository.create(razonSocial, direccion, cuit, telefono, operario);
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
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Empresa")
    @MemberOrder(sequence = "2")

    public Empresa findByCuit(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por cuit: ")
            final Empresa empresa)
    {
        return empresa;
    }

    public List<Empresa> choices0FindByCuit() { return empresaRepository.Listar();}


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de empresas")
    @MemberOrder(sequence = "2")
    public java.util.List<Empresa> listAll(){
        return empresaRepository.Listar();
    }



    @javax.inject.Inject
    EmpresaRepository empresaRepository;
}