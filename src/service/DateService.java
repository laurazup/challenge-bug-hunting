package service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
    static SimpleDateFormat simpleDateFormat;

    DateService() {
        try {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setLenient(false);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Não foi possível criar o formatador de datas");
        }
    }

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String dateFormat, ParsePosition parsePosition){
        Date date = null;

        try {
            date = simpleDateFormat.parse(dateFormat, parsePosition);
        } catch (NullPointerException e) {
            System.err.println("Erro na análise data!");
        }

        return date;
    }

}
