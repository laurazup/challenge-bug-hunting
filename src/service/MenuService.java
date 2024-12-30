package service;

import model.MenuType;

import java.util.Scanner;

// - Validar título
// - Validar descrição
// - Validar duração
// - Adicionar interactCategory
// - Validar categoria
// - Validar data
public class MenuService {
    private static int rangeOfOptions;
    private final int exitOrdinal;
    private static Scanner scanner;

    public MenuService() {
        rangeOfOptions = MenuType.values().length;
        exitOrdinal = MenuType.EXIT.ordinal() + 1;
        scanner = new Scanner(System.in);
    }

    public boolean interactMenu() {
        boolean isRepeat;
        MenuType chosenOption;
        int toDo = 0;

        MenuType.showMenu();
        chosenOption = validateMenu();

        switch (chosenOption) {
            case ADDVIDEO -> {
                toDo = 1;
                isRepeat = true;
            }
            case EDITVIDEO -> {
                toDo = 2;
                isRepeat = true;
            }
            case LISTVIDEOS -> {
                toDo = 3;
                isRepeat = true;
            }
            case REMOVEVIDEO -> {
                toDo = 4;
                isRepeat = true;
            }
            case SORTVIDEOBYDATE -> {
                toDo = 5;
                isRepeat = true;
            }
            case SEARCHVIDEOSBYTITLE -> {
                toDo = 6;
                isRepeat = true;
            }
            case SHOWDSTATISTICREPORT -> {
                toDo = 7;
                isRepeat = true;
            }
            case FILTERVIDEOBYCATEGORY -> {
                toDo = 8;
                isRepeat = true;
            }
            case EXIT -> {
                toDo = 9;
                scanner.close();
                isRepeat = false;
            }
            default -> isRepeat = false;
        }

        System.out.println(toDo);
        return isRepeat;
    }

    private MenuType validateMenu() {
        boolean isNotValid = true;
        int chosenOption;

        MenuType.showMenu();

        chosenOption = exitOrdinal;
        while (isNotValid) {
            System.out.print("Escolha uma opção entre 1 e " + rangeOfOptions + ": ");
            if (scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
                if (chosenOption > 0 && chosenOption <= rangeOfOptions) {
                    isNotValid = false;
                } else {
                    System.out.println("O número digitado está fora da faixa de valores.");
                }
            } else {
                System.out.println("A entrada digitada não é um número");
                if (scanner.hasNextLine()) {
                    scanner.next();
                }
                scanner.nextLine();
            }
        }

        return MenuType.values()[chosenOption];
    }

    private int validateDurationInMinutes() {
        boolean isNotValid = true;
        int chosenOption = 1;

        while (isNotValid) {
            System.out.print("Digite a duração do vídeo (em minutos): ");
            if (scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
                if (chosenOption > 0) {
                    isNotValid = false;
                } else {
                    System.out.println("O número digitado não é maior que zero.");
                }
            } else {
                System.out.println("A entrada digitada não é um número estritamente positivo");
                if (scanner.hasNextLine()) {
                    scanner.next();
                }
                scanner.nextLine();
            }
        }

        return chosenOption;
    }
}
