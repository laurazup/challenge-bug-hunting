package model;

public enum AttributeType {
    TITLE("Título"),
    DESCRIPTION("Descrição"),
    DURATIONINMINUTES("Duração em minutos"),
    CATEGORY("Categoria"),
    PUBLICATIONDATE("Data de publicação"),
    EXIT("Sair");

    private final String description;

    AttributeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void showAttribute() {
        for (AttributeType attribute : AttributeType.values()) {
            System.out.println(attribute.ordinal() + 1 + ". " + attribute.getDescription());
        }
    }
}
