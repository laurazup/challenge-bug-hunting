package service;

import model.AttributeType;
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
        chosenOption = MenuType.values()[validateService.validateOption(
                MenuType.values().length,
                MenuType.EXIT.ordinal()
        )];

        switch (chosenOption) {
            case ADDVIDEO -> {
                System.out.println("===== Adição de vídeo =====");
                videoService.addVideo(
                        new Video(
                                validateService.validateTitle(),
                                validateService.validateDescription(),
                                validateService.validateDurationInMinutes(),
                                validateService.validateCategory(),
                                validateService.validatePublicationDate()
                        )
                );
                System.out.println("Video adicionado com sucesso!");
                isReInteractMenu = true;
                break;
            }
            case LISTVIDEOS -> {
                System.out.println("===== Lista de Vídeos =====");
                videoService.listVideos();
                isReInteractMenu = true;
                break;
            }
            case SEARCHVIDEOSBYTITLE -> {
                System.out.println("===== Busca de vídeo que contem o texto =====");
                videoService.searchVideosByTitle(
                        validateService.validateTitle()
                );
                isReInteractMenu = true;
            }
            case EDITVIDEO -> {
                int indexOfVideo;
                System.out.println("===== Editar vídeo =====");
                indexOfVideo = videoService.indexVideoEdit(
                        validateService.validateTitle()
                );

                if (indexOfVideo != -1) {
                    boolean hasChange = false;

                    while (interactAttribute(indexOfVideo)) {
                        hasChange = true;
                    }
                }

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
            case FILTERVIDEOSBYCATEGORY -> {
                System.out.println("===== Filtrar vídeos por categoria =====");
                videoService.filterVideosByCategory(
                        validateService.validateCategory()
                );
                isReInteractMenu = true;
            }
            case SORTVIDEOBYDATE -> {
                System.out.println("===== Vídeos ordenado por data =====");
                videoService.sortVideoByDate();
                isReInteractMenu = true;
            }
            case SHOWSTATISTICREPORT -> {
                toDo = 7;
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

    private boolean interactAttribute(int indexOfVideo) {
        AttributeType chosenOption;
        boolean isReInteractAttribute = false;
        int toDo = 0;

        AttributeType.showAttribute();
        chosenOption = AttributeType.values()[validateService.validateOption(
                AttributeType.values().length,
                AttributeType.EXIT.ordinal()
        )];

        switch (chosenOption) {
            case TITLE -> {
                videoService.editVideoTitle(
                        validateService.validateTitle(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
                break;
            }
            case DESCRIPTION -> {
                videoService.editVideoDescription(
                        validateService.validateDescription(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
                break;
            }
            case DURATIONINMINUTES -> {
                videoService.editVideoDurationInMinutes(
                        validateService.validateDurationInMinutes(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
                break;
            }
            case CATEGORY -> {
                videoService.editVideoCategory(
                        validateService.validateCategory(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
                break;
            }
            case PUBLICATIONDATE -> {
                videoService.editVideoPublicationDate(
                        validateService.validatePublicationDate(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
                break;
            }
            case EXIT -> {
                isReInteractAttribute = false;
                break;
            }
            default -> {
                toDo = 7;
                break;
            }
        }
        return isReInteractAttribute;
    }
}
