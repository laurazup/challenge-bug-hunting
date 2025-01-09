package model;

import java.util.Arrays;

public enum Category {
    FILM("Filme"),
    SERIE("Série"),
    DOCUMENTARY("Documentário"),
    ANIMATION("Animação"),
    INTERVIEW("Entrevista"),
    MUSIC("Clipe musical"),
    COURSE("Curso"),
    OTHER("Outra categoria");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static boolean isValidCategory(String category) {
        return Arrays.stream(values())
                .anyMatch(cat -> cat.toString().equalsIgnoreCase(category));
    }

    public static Category fromString(String category) {
        return Arrays.stream(values())
                .filter(cat -> cat.toString().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + category));
    }
}