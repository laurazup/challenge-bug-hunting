package model;

public enum CategoryType {
    MOVIE("Filme"), SERIES("Série"), DOCUMENTARY("Documentário"), ANIMATION("Animação"), SHORT_FILM("Curta-metragem"), SHOW("Show"), REALITY_SHOW("Reality Show"), SPORTS("Esporte"), OTHERS("Outros");

    private String description;

    CategoryType(String description) {
        this.description = description;
    }
}
