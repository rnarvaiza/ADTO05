package crud;

import conexion.Conexion;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Rafa Narvaiza
 *
 * Actualiza el año de edición del Libro cuyo id es 2
 */

public class UpdateField {


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
            ResourceSet result = xpqs.query("update value //libros/Libro[id = 2]/publicacion with 1770");
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {
                res = i.nextResource();
                System.out.println(res.getContent());

                System.out.println("El campo " + res.getContent() + " ha sido actualizado");
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
