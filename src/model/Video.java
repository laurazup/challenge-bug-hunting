package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class Video {
    private String title;
    private String description;
    private int durationInMinutes;
    private String category;
    private Date publicationDate;

    public Video(String title, String description, int durationInMinutes, String category, Date publicationDate) {
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.category = category;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getCategory() {
        return category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    // Remover método sobrescrito
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return title + ";" + description + ";" + durationInMinutes + ";" + category + ";" + sdf.format(publicationDate);
    }

    @Override
    public boolean equals(Object videoObject) {
        if (videoObject == null || getClass() != videoObject.getClass()) return false;
        Video newVideo = (Video) videoObject;
        return Objects.equals(this.title.toUpperCase(), newVideo.title.toUpperCase());
    }

    // ¡¡¡ THE VIDEO CLASS CONSIDERS THE TITLE TO BE A PRIMARY KEY !!!
    // This should be checked in the business rules of video services
    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public static Video receivesFromCSV(String tupleCSV) {
        try {
            String[] partsOfCSV = tupleCSV.split(";");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partsOfCSV[0],
                    partsOfCSV[1],
                    Integer.parseInt(partsOfCSV[2]),
                    partsOfCSV[3],
                    simpleDateFormat.parse(partsOfCSV[4]));

        } catch (PatternSyntaxException e) {
            System.err.println("Não foi possível dividir a linha do arquivo CSV");
        } catch (NumberFormatException e) {
            System.err.println("Não foi possível converter a duração em minutos do Vídeo");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Não foi possível criar o formatador de datas");
        } catch (ParseException e) {
            System.err.println("Não foi possível transfomar o texto em uma data");
        }

        System.err.println("Não possível criar o vídeo");
        return null;
    }

    public String sendsToCSV() {
        String tupleCSV = "";

        try {
            tupleCSV += title + ";";
            tupleCSV += description + ";";
            tupleCSV += durationInMinutes + ";";
            tupleCSV += category + ";";
            tupleCSV += new SimpleDateFormat("dd/MM/AAAA").format(publicationDate);
        } catch (RuntimeException e) {
            System.out.println("Não foi possível converter a data.");
        }

        return tupleCSV;
    }
}