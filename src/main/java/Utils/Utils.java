package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /**
     * Constructor to get two Strings from editText and convert it to a proper format date in order to insert on db.
     * @param fecha
     * @return
     */


    public static Date returnDateFromString(String fecha) {
        Date date = null;
        StringBuilder strBuilder = new StringBuilder(fecha);
        String sDate = strBuilder.toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date =  simpleDateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
