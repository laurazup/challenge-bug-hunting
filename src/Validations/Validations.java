package Validations;

import model.CategoryType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validations {
    public static String readNonEmptyString(Scanner scanner, String prompt, String msg) {
        String input = null;
        while (input == null || input.trim().isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println(msg);
            }
        }
        return input;
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        int value = 0;
        while (value <= 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
        return value;
    }

    public static String selectCategory(Scanner scanner) {
        System.out.println("Selecione a categoria:");
        for (CategoryType category : CategoryType.values()) {
            System.out.println(category.ordinal() + 1 + " - " + category.getCategory());
        }

        int choice = 0;
        while (choice < 1 || choice > CategoryType.values().length) {
            System.out.print("Digite o número da categoria do vídeo: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
        return CategoryType.values()[choice - 1].toString();
    }

    public static Date readValidDate(Scanner scanner, String prompt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        boolean dataInvalida = true;

        Date dataDigitada = null;

        while (dataInvalida) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                dataDigitada = sdf.parse(input);
                dataInvalida = false;
            } catch (ParseException e) {
                System.out.println("Data inválida. Por favor, insira uma data no formato dd/MM/yyyy.");

            }
        }
        return dataDigitada;
    }
}