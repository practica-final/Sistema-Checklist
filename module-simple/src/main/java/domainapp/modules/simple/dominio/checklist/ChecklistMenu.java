package domainapp.modules.simple.dominio.checklist;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import domainapp.modules.simple.dominio.vehiculo.VehiculoRepository;
import org.apache.isis.applib.annotation.*;

import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType ="Checklist",
        repositoryFor = Checklist.class
)
@DomainServiceLayout(
        named = "Checklist",
        menuOrder =""
)

public class ChecklistMenu {

    @Action()
    @ActionLayout(named = "Crear Checklist")
    @MemberOrder(sequence = "1")
    public Checklist create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Id Checklist")
            final String idChecklist,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Documentación: ")
            final boolean documentacion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tablero: ")
            final boolean tablero,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Laterales:  ")
            final boolean laterales,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Sección Trasera:  ")
            final boolean seccionTrasera,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Frente:  ")
            final boolean frente,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Comentarios:  ")
            final String comentarios,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Fotos:  ")
            final String fotos,

            @ParameterLayout(named = "Vehículo: ")
            final Vehiculo vehiculo
    ){
        return checklistRepository.create(idChecklist, documentacion, tablero, laterales, seccionTrasera,
                frente, comentarios, fotos, vehiculo);
    }

    //@Programmatic
    public String validate0Create(final String idChecklist) {
        return ValidarIdChecklist(idChecklist);
    }
    public List<Vehiculo> choices8Create() {return vehiculoRepository.Listar();}

    @Programmatic
    private String ValidarIdChecklist(final String idChecklist) {
        if (Character.isDigit(idChecklist.charAt(0)) &&
                Character.isDigit(idChecklist.charAt(1)) &&
                (Character.compare(idChecklist.charAt(2), '-') == 0) &&
                Character.isDigit(idChecklist.charAt(3)) &&
                Character.isDigit(idChecklist.charAt(4)) &&
                Character.isDigit(idChecklist.charAt(5)) &&
                Character.isDigit(idChecklist.charAt(6)) &&
                Character.isDigit(idChecklist.charAt(7)) &&
                Character.isDigit(idChecklist.charAt(8)) &&
                Character.isDigit(idChecklist.charAt(9)) &&
                Character.isDigit(idChecklist.charAt(10)) &&
                (Character.compare(idChecklist.charAt(11), '-') == 0) &&
                Character.isDigit(idChecklist.charAt(12))) {
            return null;
        } else {
            return "Formato no valido";
        }
    }

        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Vehiculo")
        @MemberOrder(sequence = "2")
        public Checklist findByIdChecklist(
        @Parameter(optionality = Optionality.MANDATORY)
        @ParameterLayout(named = "Por Id Checklist: ")
        final Checklist checklist) {

            return checklist;
        }

        public List<Checklist> choices0FindByIdChecklist() {return checklistRepository.Listar();}

        @Action(semantics = SemanticsOf.SAFE)
        @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Checklist")
        @MemberOrder(sequence = "2")
        public java.util.List<Checklist> listAll() {
            return checklistRepository.Listar();
        }

    @javax.inject.Inject
    ChecklistRepository checklistRepository;

    @javax.inject.Inject
    VehiculoRepository vehiculoRepository;
}
