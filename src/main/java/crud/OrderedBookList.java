package crud;

import conexion.Conexion;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author Rafa Narvaiza
 *
 * 2.2 Listado de libros, publicados después del año 2000, junto con el número de veces que ha sido prestado. Ordena el resultado por nombre del autor.
 */

public class OrderedBookList {

    public static void main(String[] args) throws ClassNotFoundException, XMLDBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = Class.forName(Conexion.DRIVER);
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        ////Libro[publicacion > 2000]/id
        try {
            col = DatabaseManager.getCollection(Conexion.URI + Conexion.COLLECTION, Conexion.USERNAME, Conexion.PASSWORD);
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            ResourceSet result = xpqs.query("distinct-values(//Libro[publicacion > 2000]/id)");
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {
                res = i.nextResource();
                System.out.println(res.getContent());
                result = xpqs.query("count(//prestamo/Libro[. = " + res.getContent() + "])");
                ResourceIterator iteratorCounter = result.getIterator();
                Resource counter = iteratorCounter.nextResource();
                System.out.println("El Libro " + res.getContent() + ", se ha prestado: " + counter.getContent() + " veces");
            }
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
