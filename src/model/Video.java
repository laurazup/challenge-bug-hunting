package model;

public class Video {
    private String title;
    private String description;
    private int duration; // em minutos
    private Category category;
    private String date;

    public Video(String title, String description, int duration, Category category, String date) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.date = date;
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

    public Category getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return title + ";" + description + ";" + duration + ";" + category + ";" + date;
    }

    public static Video fromString(String linha) {
        try {
            String[] parts = linha.split(";");
            return new Video(parts[0], parts[1], Integer.parseInt(parts[2]), Category.isValidCategoria(parts[3]), parts[4]);
        } catch (Exception e) {
            return null;
        }
    }
}