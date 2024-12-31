package service;

import model.CategoryType;
import model.MenuType;
import model.Video;

public class MenuService {
    VideoService videoService;
    ValidateService validateService;


    public MenuService(String filePathname) {
        videoService = new VideoService(filePathname);
        validateService = new ValidateService();
    }

    public boolean interactMenu() {
        boolean isReInteractMenu;
        MenuType chosenOption;
        int toDo = 0;

        MenuType.showMenu();
        chosenOption = MenuType.values()[validateService.validateMenu(
                MenuType.values().length,
                MenuType.EXIT.ordinal())];

        switch (chosenOption) {
            case ADDVIDEO -> {
                System.out.println("===== Adição de vídeo =====");
                videoService.addVideo(
                        new Video(
                                validateService.validateTitle(),
                                validateService.validateTitle(),
                                validateService.validateDurationInMinutes(),
                                validateService.validateCategory(),
                                validateService.validatePublicationDate()
                        )
                );
                System.out.println("Video adicionado com sucesso!");
                isReInteractMenu = true;
                break;
            }
            case EDITVIDEO -> {
                toDo = 2;
                // CategoryType value = CategoryType.values()[validateService.validateCategory(CategoryType.values().length)];
                isReInteractMenu = true;
                break;
            }
            case LISTVIDEOS -> {
                System.out.println("===== Lista de Vídeos =====");
                videoService.listVideos();
                isReInteractMenu = true;
                break;
            }
            case REMOVEVIDEO -> {
                System.out.println("===== Remoção de vídeo =====");
                videoService.removeVideo(
                        validateService.validateTitle()
                );
                System.out.println("Video removido com sucesso!");
                isReInteractMenu = true;
                break;
            }
            case SORTVIDEOBYDATE -> {
                System.out.println("===== Vídeos ordenado por data =====");
                videoService.sortVideoByDate();
                isReInteractMenu = true;
            }
            case SEARCHVIDEOSBYTITLE -> {
                System.out.println("===== Busca de vídeo que contem o texto =====");
                String titleToSearch = validateService.validateTitle();
                videoService.searchVideosByTitle(
                        validateService.validateTitle()
                );
                isReInteractMenu = true;
            }
            case SHOWDSTATISTICREPORT -> {
                toDo = 7;
                isReInteractMenu = true;
            }
            case FILTERVIDEOSBYCATEGORY -> {
                System.out.println("===== Filtrar vídeos por categoria =====");
                videoService.filterVideosByCategory(
                        CategoryType.values()[
                                validateService.validateCategory()
                                ]
                );
                isReInteractMenu = true;
            }
            case EXIT -> {
                System.out.println("Saindo do sistema...");
                validateService.close();
                isReInteractMenu = false;
            }
            default -> {
                System.err.println("Opção inválida.");
                validateService.close();
                isReInteractMenu = false;
            }
        }

        System.out.println(toDo);
        return isReInteractMenu;
    }
}
