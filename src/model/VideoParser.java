package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class VideoParser {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private VideoParser() {
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("A data não pode ser nula ou vazia.");
        }

        sdf.setLenient(false);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato " + DATE_FORMAT + ".");
        }
    }

    private static final Set<String> VALID_CATEGORIES = Set.of("FILME", "SERIE", "DOCUMENTARIO");
    public static VideoModel.VideoCategory validateAndParseCategory(String categoryInput) {
        if (categoryInput == null || categoryInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A categoria não pode estar vazia.");
        }
        try {
            return VideoModel.VideoCategory.valueOf(categoryInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: " + VALID_CATEGORIES);
        }
    }
}
