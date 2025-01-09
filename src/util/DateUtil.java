package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static boolean isValidDate(String dateInput) {
        SimpleDateFormat sdf = createDateFormatter();
        try {
            sdf.parse(dateInput);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date parseDate(String dateInput) {
        SimpleDateFormat sdf = createDateFormatter();
        try {
            return sdf.parse(dateInput);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato inválido");
        }
    }

    public static String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    private static SimpleDateFormat createDateFormatter() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        dateFormatter.setLenient(false);
        return dateFormatter;
    }
}
