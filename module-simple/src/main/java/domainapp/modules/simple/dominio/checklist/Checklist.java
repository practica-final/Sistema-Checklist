package domainapp.modules.simple.dominio.checklist;

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dominio.ObservadorGeneral;
import domainapp.modules.simple.dominio.operario.Operario;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;

public class Checklist implements Comparable<Checklist> {

    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Property(hidden = Where.EVERYWHERE)
    @Title()
    private int idChecklist;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private boolean documentacion;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private boolean tablero;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private boolean laterales;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private boolean seccionTrasera;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private boolean frente;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private String comentarios;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private String fotos;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private Operario operario;

    @Column(allowsNull = "false", length = 20)
    @Property()
    @Title()
    private Vehiculo vehiculo;


    public Checklist(
            int idChecklist,
            boolean documentacion, boolean tablero, boolean laterales, boolean seccionTrasera,
            boolean frente,
            String comentarios, String fotos
    ){
        this.idChecklist = idChecklist;
        this.documentacion = documentacion;
        this.tablero = tablero;
        this.laterales = laterales;
        this.seccionTrasera = seccionTrasera;
        this.frente = frente;
        this.comentarios = comentarios;
        this.fotos = fotos;
    }

    public int getIdChecklist() { return this.idChecklist; }
    public boolean getDocumentacion() { return this.documentacion; }
    public boolean getTablero() { return this.tablero; }
    public boolean getLaterales() { return this.laterales; }
    public boolean getSeccionTrasera() { return this.seccionTrasera; }
    public boolean getFrente() { return this.frente; }
    public String getComentarios() { return this.comentarios; }
    public String getFotos() { return this.fotos; }

    @Override
    public int compareTo(final Checklist other) {
        return ComparisonChain.start()
                .compare(this.getIdChecklist(), other.getIdChecklist())
                .result();
    }


}
