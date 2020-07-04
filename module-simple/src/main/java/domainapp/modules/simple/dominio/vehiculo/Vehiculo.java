package domainapp.modules.simple.dominio.vehiculo;

import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;

import javax.jdo.annotations.*;

@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "dominio",
        table = "Vehiculo"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "ORDER BY dominio ASC"),
        @Query(
                name = "findByDominioContains", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "WHERE dominio.indexOf(:dominio) >= 0 "),
        @Query(
                name = "findByDominio", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "WHERE dominio == :dominio "),
        @Query(
                name = "findByEmpresa", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "WHERE empresa == :empresa "
                        + "ORDER BY dominio ASC"),
        @Query(
                name = "findByEstado", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "WHERE estado == :estado "
                        + "ORDER BY dominio ASC"),
        @Query(
                name = "findByEmpresaAndEstado", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dominio.vehiculo.Vehiculo "
                        + "WHERE empresa == :empresa && estado == :estado "
                        + "ORDER BY dominio ASC")
})

@Unique(name = "Vehiculo_dominio_UNQ", members = { "dominio" })
@DomainObject(
        editing = Editing.DISABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter
@Setter
public class Vehiculo {
}
