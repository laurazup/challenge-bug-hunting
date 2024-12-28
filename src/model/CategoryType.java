package model;

public enum CategoryType {
    ANIMATION("Animação"),
    CHILDREN("Infantil"),
    DOCUMENTARY("Documentário"),
    EDUCATIONAL("Educacional"),
    MOVIE("Filme"),
    SERIES("Série"),
    SHOW("Show"),
    SPORTS("Esporte"),
    OTHERS("Outros");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void showCategory() {
        for (CategoryType category : CategoryType.values()) {
            System.out.println(category.ordinal() + 1 + ". " + category.getDescription());
        }
    }
}
