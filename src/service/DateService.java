package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
    SimpleDateFormat simpleDateFormat;

    DateService() {
        try {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setLenient(false);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Não foi possível criar o formatador de datas");
        }
    }

    public String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public Date stringToDate(String dateFormat){
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateFormat);
        } catch (ParseException e) {
            System.err.println("Erro na análise data!");
        }

        return date;
    }

}
