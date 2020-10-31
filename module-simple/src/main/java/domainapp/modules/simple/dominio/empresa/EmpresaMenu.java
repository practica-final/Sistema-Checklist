package domainapp.modules.simple.dominio.empresa;

import domainapp.modules.simple.dominio.EstadoGeneral;
import domainapp.modules.simple.dominio.operario.QOperario;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.empresa.EmpresaRepository;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.List;

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

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Estado: ")
            final EstadoGeneral estado

            /*@ParameterLayout(named = "Operario: ")
            final Operario operario*/

    ) {

        return empresaRepository.create(razonSocial, direccion, cuit, telefono, estado);
    }

    /*public String validate0Create (final String dominio) {
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

    }*/

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Empresa")
    @MemberOrder(sequence = "2")
    public Empresa findByCuit(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por cuit: ")
            final String cuit) {
        TypesafeQuery<Empresa> q = isisJdoSupport.newTypesafeQuery(Empresa.class);
        final QEmpresa cand = QEmpresa.candidate();
        q = q.filter(
                cand.cuit.eq(q.stringParameter("cuit"))
        );
        return q.setParameter("cuit", cuit)
                .executeUnique();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de empresas")
    @MemberOrder(sequence = "3")
    public List<Empresa> listAll(){
        return repositoryService.allInstances(Empresa.class);
    }

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    @javax.inject.Inject
    EmpresaRepository empresaRepository;
}