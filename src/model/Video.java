package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String title;
    private String description;
    private double duration;
    private String category;
    private Date publicationDate;


    public Video(String title, String description, double duration, String category, String publicationDate) {
        setTitle(title);
        setDescription(description);
        setDuration(duration);
        setCategory(category);
        setPublicationDate(publicationDate);
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração deve ser um número positivo.");
        }
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || (!category.equalsIgnoreCase("Filme") && !category.equalsIgnoreCase("Série") && !category.equalsIgnoreCase("Documentário"))) {
            throw new IllegalArgumentException("Categoria inválida. Use 'Filme', 'Série' ou 'Documentário'.");
        }
        this.category = category;
    }


    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        try {
            this.publicationDate = new SimpleDateFormat("dd/MM/yyyy").parse(publicationDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    @Override
    public String toString() {
        return "Vídeo [Título=" + title + ", Descrição=" + description + ", Duração=" + duration + " minutos, Categoria=" + category + ", Data de Publicação=" + publicationDate + "]";
    }
}
