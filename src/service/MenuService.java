package service;

import model.MenuType;

import java.util.Scanner;

public class MenuService {
    private static final int rangeOfOptions = MenuType.values().length;
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean interact() {
        boolean isOutsideRange;
        MenuType.showMenu();
        int chosenOption;
        do {
            System.out.print("Escolha uma opção entre 1 e" + rangeOfOptions + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("A entrada digitada não é um número");
                scanner.next();
            }
            chosenOption = scanner.nextInt();
            isOutsideRange = chosenOption <= 0 || chosenOption > rangeOfOptions;
            if (isOutsideRange) {
                System.out.println("O número digitado está fora da faixa de valores.");
            }
        } while (isOutsideRange);

        return chosenOption != MenuType.EXIT.ordinal() + 1;
    }
}
