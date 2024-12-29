package service;

import model.MenuType;

import java.util.Scanner;

public class MenuService {
    private static int rangeOfOptions = 0;
    private static Scanner scanner = null;

    public MenuService() {
        rangeOfOptions = MenuType.values().length;
        scanner = new Scanner(System.in);
    }

    public boolean interact() {
        boolean isOutsideRange;
        boolean isNotValid = true;
        int chosenOption;
        int exitOrdinal = MenuType.EXIT.ordinal() + 1;

        MenuType.showMenu();

        chosenOption = exitOrdinal;
        while (isNotValid) {
            System.out.print("Escolha uma opção entre 1 e " + rangeOfOptions + ": ");
            if(scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
                if(chosenOption > 0 && chosenOption <= rangeOfOptions) {
                    isNotValid = false;
                } else {
                    System.out.println("O número digitado está fora da faixa de valores.");
                }
            } else {
                System.out.println("A entrada digitada não é um número");
                if(scanner.hasNextLine()){
                    scanner.next();
                }
                scanner.nextLine();
            }
        }

        if (chosenOption == exitOrdinal){
            scanner.close();
            return false;
        }
        return true;
    }
}
