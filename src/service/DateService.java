package service;

import java.text.SimpleDateFormat;

public class DateService {
    SimpleDateFormat simpleDateFormat;

    DateService() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
    }

}
