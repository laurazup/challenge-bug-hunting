package model;

public enum CategoryType {
    FILME("Filme"),
    SERIE("Série"),
    DOCUMENTARIO("Documentário"),
    CURTAMETRAGEM("Curta-metragem"),
    SHOW("Show"),
    ESPORTE("Esporte"),
    ANIMACAO("Animação");

    private final String category;

    CategoryType(String category) {
        this.category = category;
    }

    public String getCategory(){
        return category;
    }
}
