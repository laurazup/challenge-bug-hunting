package util;

import java.util.Date;
import java.util.Scanner;

import static util.DateUtil.isValidDate;
import static util.DateUtil.parseDate;

public final class ScannerUtil {

    public static String readString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (!input.isEmpty())
                return input;
        }
    }

    public static int readInt(Scanner scanner, String message) {
        int option;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
                return option;
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
            }
        }
    }

    public static Date readDateFromScanner(Scanner scanner, String message) {
        while (true) {
            String userInput = readString(scanner, message);
            if (isValidDate(userInput))
                return parseDate(userInput);
        }
    }
}
