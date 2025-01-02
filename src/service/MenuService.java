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
        boolean isReInteractMenu = false;
        MenuType chosenOption;

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
            }
            case LISTVIDEOS -> {
                System.out.println("===== Lista de Vídeos =====");
                videoService.listVideos();
                isReInteractMenu = true;
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

                    if (hasChange) {
                        videoService.editSaveVideo();
                    }
                }

                isReInteractMenu = true;
            }
            case REMOVEVIDEO -> {
                System.out.println("===== Remoção de vídeo =====");
                videoService.removeVideo(
                        validateService.validateTitle()
                );
                System.out.println("Video removido com sucesso!");
                isReInteractMenu = true;
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
                int[] listByCategory;

                System.out.println("===== Estatísticas dos vídeos =====");
                listByCategory = videoService.showStatisticReport(AttributeType.values().length);

                System.out.println("Quantidade de vídeos por categoria:");
                for (int index = 0; index < listByCategory.length; index++) {
                    System.out.println(AttributeType.values()[index]
                            .getDescription() + ": "
                            + listByCategory[index]);
                }

                isReInteractMenu = true;
            }
            case EXIT -> {
                System.out.println("Saindo do Menu...");
                validateService.close();
                // isReInteractMenu = false;
            }
        }

        return isReInteractMenu;
    }

    private boolean interactAttribute(int indexOfVideo) {
        AttributeType chosenOption;
        boolean isReInteractAttribute = false;

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
            }
            case DESCRIPTION -> {
                videoService.editVideoDescription(
                        validateService.validateDescription(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
            }
            case DURATIONINMINUTES -> {
                videoService.editVideoDurationInMinutes(
                        validateService.validateDurationInMinutes(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
            }
            case CATEGORY -> {
                videoService.editVideoCategory(
                        validateService.validateCategory(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
            }
            case PUBLICATIONDATE -> {
                videoService.editVideoPublicationDate(
                        validateService.validatePublicationDate(),
                        indexOfVideo
                );
                isReInteractAttribute = true;
            }
            case EXIT -> {
                // isReInteractAttribute = false;
            }
        }
        return isReInteractAttribute;
    }
}
