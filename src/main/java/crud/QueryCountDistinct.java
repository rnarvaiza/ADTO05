package crud;

import conexion.Conexion;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author Rafa Narvaiza
 *
 * 2.1 Para cada Libro, obtener número de veces prestado.
 */

public class QueryCountDistinct {

    public static void main(String[] args) throws ClassNotFoundException, XMLDBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = Class.forName(Conexion.DRIVER);
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        try {
            col = DatabaseManager.getCollection(Conexion.URI + Conexion.COLLECTION, Conexion.USERNAME, Conexion.PASSWORD);
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            ResourceSet result = xpqs.query("distinct-values(//prestamos/prestamo/libro)");
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {
                res = i.nextResource();
                result = xpqs.query("count(//prestamos/prestamo/libro[. = " + res.getContent() + "])");
                ResourceIterator iteratorCounter = result.getIterator();
                Resource counter = iteratorCounter.nextResource();
                System.out.println("El Libro " + res.getContent() + ", se ha prestado: " + counter.getContent() + " veces");
            }


            /*
            ResourceSet result = xpqs.query("for $a in distinct-values( //prestamo/libro)  let $c:= count(for $b in /prestamos/prestamo/libro where $b=$a return $b) return <prestamo> <libro> {$a}</libro><veces>{$c}</veces></prestamo>" );
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {
                res = i.nextResource();
                System.out.println(res.getContent());

            }

             */
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }
}
