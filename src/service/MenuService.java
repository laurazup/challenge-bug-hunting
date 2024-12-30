package service;

import model.MenuType;

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
                toDo = 1;
                // CategoryType value = CategoryType.values()[validateService.validateCategory(CategoryType.values().length)];
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
                toDo = 3;
                isReInteractMenu = true;
                break;
            }
            case REMOVEVIDEO -> {
                toDo = 4;
                isReInteractMenu = true;
                break;
            }
            case SORTVIDEOBYDATE -> {
                toDo = 5;
                isReInteractMenu = true;
            }
            case SEARCHVIDEOSBYTITLE -> {
                toDo = 6;
                isReInteractMenu = true;
            }
            case SHOWDSTATISTICREPORT -> {
                toDo = 7;
                isReInteractMenu = true;
            }
            case FILTERVIDEOBYCATEGORY -> {
                toDo = 8;
                isReInteractMenu = true;
            }
            case EXIT -> {
                toDo = 9;
                validateService.close();
                isReInteractMenu = false;
            }
            default -> isReInteractMenu = false;
        }

        System.out.println(toDo);
        return isReInteractMenu;
    }
}
