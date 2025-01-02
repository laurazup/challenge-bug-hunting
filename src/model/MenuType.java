package model;

public enum MenuType {
    ADD_VIDEO("Adicionar vídeo"),
    LIST_VIDEOS("Listar vídeos"),
    SEARCH_VIDEOS_BY_TITLE("Pesquisar vídeo por título"),
    EDIT_VIDEO("Editar vídeo"),
    REMOVE_VIDEO("Excluir vídeo"),
    FILTER_VIDEOS_BY_CATEGORY("Filtrar vídeos por categoria"),
    SORT_VIDEO_BY_DATE("Ordenar vídeos por data de publicação"),
    SHOW_STATISTICS_REPORT("Exibir relatório de estatísticas"),
    EXIT("Sair");
    // BUSINESS RULE: the exit menu must be the last one on the list.

    private final String description;

    MenuType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void showMenu() {
        for (MenuType menu : MenuType.values()) {
            System.out.println(menu.ordinal() + 1 + ". " + menu.getDescription());
        }
    }
}
