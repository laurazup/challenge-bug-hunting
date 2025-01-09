package model;

public enum PrincipalMenu {

    ADD("Adicionar vídeo"),
    LIST("Listar vídeos"),
    SEARCH_BY_TITLE("Pesquisar vídeo por título"),
    EDIT("Editar vídeo"),
    DELETE("Excluir vídeo"),
    VIDEOS_BY_CATEGORY("Filtrar vídeos por categoria"),
    SORT_BY_DATE("Ordenar vídeos por data de publicação"),
    REPORT ("Exibir relatório de estatísticas"),
    EXIT("Sair");

    private String description;

    PrincipalMenu(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
