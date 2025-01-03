package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.logging.FileHandler;

public class VideoModel {

    private String title;
    private String description;
    private int duration;
    private VideoCategory category;
    private Date publicationDate;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    static {
        DATE_FORMATTER.setLenient(false);
    }

    public VideoModel(String title, String description, int duration, VideoCategory category, String publicationDate) {
        validateTitle(title);
        validateDescription(description);
        validateDuration (duration);

        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.publicationDate = validateAndParseDate(publicationDate);
    }

    public void setPublicationDate(Date publicationDate) {
        if (publicationDate == null) {
            throw new IllegalArgumentException(" A data de publicação precisa ser preenchida.");
        }
        this.publicationDate = publicationDate;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public void setDuration(int duration) {
        validateDuration(duration);
        this.duration = duration;
    }

    public void setCategory(VideoCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public VideoCategory getCategory() {
        return category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O titulo não pode estar vazio");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode esta vazio.");
        }
    }

    private void validateDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração deve ser um número inteiro.");
        }
    }

    private Date validateAndParseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return DATE_FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy.");
        }
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return title + "; " + description + "; " + duration + "; " + category + "; " + DATE_FORMATTER.format(publicationDate);
    }

    public static VideoModel fromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos para continuar.");
        }

        try {
            String[] parts = line.split(";");
            if (parts.length != 5) {
                throw new IllegalArgumentException("Formato de dados invalido. São esperados 5 campos separados por ';'.");
            }

            String title = parts[0].trim();
            String description = parts[1].trim();
            int duration = Integer.parseInt(parts[2].trim());
            String categoryStr = parts[3].trim();
            String publicationDateStr = parts[4].trim();

            VideoCategory category = VideoCategory.fromString(categoryStr);
            Date publicationDate = DATE_FORMATTER.parse(publicationDateStr);


            return new VideoModel(title, description, duration, category, publicationDateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao processar a linha de entrada: " + e.getMessage(), e);
        }
    }

    public enum VideoCategory {
            VIDEO, SERIE, DOCUMENTARIO;

        public static VideoCategory validateAndParseCategory(String category) {
            if (category == null || category.trim().isEmpty()){
                throw new IllegalArgumentException("Categoria inválida. Use FILME, SERIE ou DOCUMENTARIO.");
            }
            try{
                return VideoCategory.valueOf(category.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Categoria inválida. Use FILME, SERIE ou DOCUMENTARIO.");

            }
        }

        public static VideoCategory fromString(String categoryStr) {
            return null;
        }

    }
}
