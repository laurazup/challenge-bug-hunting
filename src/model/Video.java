package model;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }

    public String toCSV() {
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