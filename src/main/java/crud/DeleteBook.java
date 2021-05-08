package crud;

import Utils.Utils;
import conexion.Conexion;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;
import pojo.Prestamos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class DeleteBook {
    /**
     * @author Rafa Narvaiza
     *
     * Borra un Libro cuya fecha del último préstamo sea anterior al año 2005.
     */

    public static void main(String[] args) throws ClassNotFoundException, XMLDBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = Class.forName(Conexion.DRIVER);
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        Prestamos prestamos;
        try {
            col = DatabaseManager.getCollection(Conexion.URI + Conexion.COLLECTION, Conexion.USERNAME, Conexion.PASSWORD);
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            ResourceSet result = xpqs.query("//prestamos");
            ResourceIterator i = result.getIterator();
            Resource res = null;
            String xmlStr = null;

            while (i.hasMoreResources()) {
                res = i.nextResource();
                xmlStr = res.getContent().toString();
            }
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Prestamos.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                prestamos = (Prestamos) jaxbUnmarshaller.unmarshal(new StringReader(xmlStr));
                Date date1 = Utils.returnDateFromString("01-01-2005");
                Date date2;
                final String selectedID = "4";
                for (int k =1; k<prestamos.getPrestamo().size(); k++){
                    date2 = Utils.returnDateFromString(prestamos.getPrestamo().get(k).getFechaprestamo());
                    if(date2.before(date1)){
                        //selectedID = prestamos.getPrestamo().get(k).getLibro();
                    }
                }
                ResourceSet result1 = xpqs.query("update delete //prestamos/prestamo[libro = " + selectedID + "]");
            } catch (JAXBException e) {
                e.printStackTrace();
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
