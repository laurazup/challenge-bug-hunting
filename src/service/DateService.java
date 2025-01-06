package service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
    public static final int ERROR_NOT_FOUND = -1;
    static SimpleDateFormat simpleDateFormat;
    ParsePosition parsePosition;

    public DateService() {
        try {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setLenient(false);
            parsePosition = new ParsePosition(0);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Não foi possível criar o formatador de datas");
        }
    }

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public Date stringToDate(String dateFormat) {
        Date date = null;
        parsePosition.setIndex(0);

        try {
            date = simpleDateFormat.parse(dateFormat, parsePosition);
        } catch (NullPointerException e) {
            System.err.println("Erro na análise data!");
        }

        return date;
    }

    public boolean getError() {
        return parsePosition.getErrorIndex() != ERROR_NOT_FOUND;
    }

    public void resetErrorIndex() {
        parsePosition.setErrorIndex(ERROR_NOT_FOUND);
    }

}
