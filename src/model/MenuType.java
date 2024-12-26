package model;

public enum MenuType {
    ADDVIDEO("Adicionar vídeo"),
    LISTVIDEOS("Listar vídeos"),
    SEARCHtITLE("Pesquisar vídeo por título"),
    EXIT("Sair");

    private final String description;

    MenuType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
