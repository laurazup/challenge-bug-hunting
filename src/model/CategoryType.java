package model;

public enum CategoryType {
    MOVIE("Filme"), SERIES("Série"), DOCUMENTARY("Documentário"), ANIMATION("Animação"), SHORT_FILM("Curta-metragem"), SHOW("Show"), REALITY_SHOW("Reality Show"), SPORTS("Esporte"), OTHERS("Outros");

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
