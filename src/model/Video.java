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
    private CategoryType category;
    private Date publicationDate;

    public Video(String title, String description, int durationInMinutes, int category, Date publicationDate) {
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.category = CategoryType.values()[category];
        this.publicationDate = publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setCategory(int categoryOrdinal) {
        this.category = CategoryType.values()[categoryOrdinal];
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getCategoryInOrdinal(){
        return category.ordinal();
    }

    public String getCategoryInString() {
        return category.getDescription();
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        String formattedOutput = "";
        SimpleDateFormat simpleDateFormat;

        try {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            formattedOutput += "Titulo    : " + title + "\n";
            formattedOutput += "Descrição : " + description + "\n";
            formattedOutput += "Duração   : " + durationInMinutes + "min.\n";
            formattedOutput += "Categoria : " + category.getDescription() + "\n";
            formattedOutput += "Publicação: " + simpleDateFormat.format(publicationDate) + "\n";
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Não foi possível criar o formatador de datas");
        }

        return formattedOutput;
    }

    @Override
    public boolean equals(Object potentialVideo) {
        if (potentialVideo == null ||
                this.getClass() != potentialVideo.getClass()) {
            return false;
        }
        Video newVideo = (Video) potentialVideo;
        return Objects.equals(this.title.toUpperCase(), newVideo.title.toUpperCase());
    }

    // ¡¡¡ THE VIDEO CLASS CONSIDERS THE TITLE TO BE A PRIMARY KEY !!!
    // This should be checked in the business rules of video services
    @Override
    public int hashCode() {
        return Objects.hashCode(this.title);
    }

    public static Video receivesFromCSV(String tupleCSV) {
        try {
            String[] partsOfCSV = tupleCSV.split(";");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partsOfCSV[0],
                    partsOfCSV[1],
                    Integer.parseInt(partsOfCSV[2]),
                    Integer.parseInt(partsOfCSV[3]),
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
            tupleCSV += category.ordinal() + ";";
            tupleCSV += new SimpleDateFormat("dd/MM/AAAA").format(publicationDate);
        } catch (RuntimeException e) {
            System.out.println("Não foi possível converter a data.");
        }

        return tupleCSV;
    }
}