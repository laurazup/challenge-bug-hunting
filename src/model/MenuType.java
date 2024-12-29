package model;

public enum MenuType {
    ADDVIDEO("Adicionar vídeo"),
    LISTVIDEOS("Listar vídeos"),
    SEARCHVIDEOSBYTITLE("Pesquisar vídeo por título"),
    EDITVIDEO("Editar vídeo"),
    REMOVEVIDEO("Excluir vídeo"),
    FILTERVIDEOBYCATEGORY("Filtrar vídeos por categoria"),
    SORTVIDEOBYDATE("Ordenar vídeos por data de publicação"),
    SHOWDSTATISTICREPORT("Exibir relatório de estatísticas"),
    EXIT("Sair");

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
