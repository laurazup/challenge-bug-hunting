package service;

import model.CategoryType;
import model.MenuType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuService {
    private static Scanner scanner;

    public MenuService() {
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
        int rangeOfOptions = MenuType.values().length;
        int exitOrdinal = MenuType.EXIT.ordinal();
        int chosenOption;

        MenuType.showMenu();

        chosenOption = exitOrdinal;
        while (isNotValid) {
            System.out.print("Escolha uma opção entre 1 e " + rangeOfOptions + ": ");
            if (scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
                if (chosenOption > 0 && chosenOption <= rangeOfOptions) {
                    chosenOption--;
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

    private String validateText() {
        boolean isNotValid = true;
        String inputUser = "";

        while (isNotValid) {
            inputUser = scanner.nextLine();
            if (inputUser.isBlank()) {
                System.err.println("O texto não pode ser vazia!");
            } else if (inputUser.contains(";")) {
                System.err.println("O carácter ';' não é permitido no texto!");
            } else {
                isNotValid = false;
            }
        }

        return inputUser;
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

    private CategoryType validateCategory() {
        boolean isNotValid = true;
        int rangeOfOptions = CategoryType.values().length;
        int chosenOption = 0;

        CategoryType.showCategory();

        while (isNotValid) {
            System.out.print("Escolha uma categoria entre 1 e " + rangeOfOptions + ": ");
            if (scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
                if (chosenOption > 0 && chosenOption <= rangeOfOptions) {
                    chosenOption--;
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

        return CategoryType.values()[chosenOption];
    }

    private Date validatePublicationDate() {
        boolean isNotValid = true;
        SimpleDateFormat simpleDateFormat;
        Date publicationDate = null;

        while (isNotValid) {
            try {
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                simpleDateFormat.setLenient(false);

                try {
                    System.out.print("Digite a data de publicação no formato (dd/MM/yyyy): ");
                    publicationDate = simpleDateFormat.parse(scanner.next());
                    isNotValid = false;
                } catch (ParseException e) {
                    System.err.println("A data informada é inválida!");
                }
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("Não foi possível criar o formatador de datas");
            }
        }

        return publicationDate;
    }
}
