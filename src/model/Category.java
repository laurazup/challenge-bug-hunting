package model;

import java.util.Arrays;

public enum Category {
    DOCUMENTARIO("Documentário"),
    SERIE("Série"),
    FILME("Filme"),
    ANIMACAO("Animação");

    private final String description;

    Category(String descricao) {
        this.description = descricao;
    }

    @Override
    public String toString() {
        return description;
    }

    public static Category isValidCategoria(String category) {
        return Arrays.stream(Category.values())
                .filter(c -> c.description.equalsIgnoreCase(category) || c.name().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Digite uma categoria válida!"));
    }

}
