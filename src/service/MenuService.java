package service;

import model.AttributeType;
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
        boolean isReInteractMenu = false;
        MenuType chosenOption;

        MenuType.showMenu();
        chosenOption = MenuType.values()[validateService.validateOption(
                MenuType.values().length,
                MenuType.EXIT.ordinal()
        )];

        switch (chosenOption) {
            case ADD_VIDEO -> {
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
            case LIST_VIDEOS -> {
                System.out.println("===== Lista de Vídeos =====");
                videoService.listVideos();
                isReInteractMenu = true;
            }
            case SEARCH_VIDEOS_BY_TITLE -> {
                System.out.println("===== Busca de vídeo que contem o texto =====");
                videoService.searchVideosByTitle(
                        validateService.validateTitle()
                );
                isReInteractMenu = true;
            }
            case EDIT_VIDEO -> {
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
            case REMOVE_VIDEO -> {
                System.out.println("===== Remoção de vídeo =====");
                videoService.removeVideo(
                        validateService.validateTitle()
                );
                isReInteractMenu = true;
            }
            case FILTER_VIDEOS_BY_CATEGORY -> {
                System.out.println("===== Filtrar vídeos por categoria =====");
                videoService.filterVideosByCategory(
                        validateService.validateCategory()
                );
                isReInteractMenu = true;
            }
            case SORT_VIDEO_BY_DATE -> {
                System.out.println("===== Vídeos ordenado por data =====");
                videoService.sortVideoByDate();
                isReInteractMenu = true;
            }
            case SHOW_STATISTICS_REPORT -> {
                int[] listByCategory;

                System.out.println("===== Estatísticas dos vídeos =====");
                listByCategory = videoService.showStatisticReport(CategoryType.values().length);

                System.out.println("Quantidade de vídeos por categoria:");
                for (int index = 0; index < listByCategory.length; index++) {
                    System.out.println(CategoryType.values()[index]
                            .getDescription() + ": "
                            + listByCategory[index]);
                }

                isReInteractMenu = true;
            }
            case EXIT -> {
                System.out.println("===== Saindo do Menu...");
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
            case DURATION_IN_MINUTES -> {
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
            case PUBLICATION_DATE -> {
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
