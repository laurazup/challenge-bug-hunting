package util;

import model.Category;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public String isValidString(String string, Scanner scanner) {
        String input;
        do {
            System.out.println(string);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Erro! O campo não pode estar vazio. Por favor, digite um valor!");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    public int isValidInt(String string, Scanner scanner) {
        String input;
        int value = -1;
        boolean valid = false;

        do {
            System.out.print(string);
            input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Erro! Por favor, digite um número válido!");
            } else {
                try {
                    value = Integer.parseInt(input);
                    if (value < 0) {
                        System.out.println("Erro! O número não pode ser negativo. Digite um número válido!");
                    } else {
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro! Por favor, digite um número válido!");
                }
            }
        } while (!valid);

        return value;
    }

    public boolean validDateRegex (String date) {
        String regex = "^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    public String isValidDate(String string, Scanner scanner) {
        boolean invalidDate = true;
        String input;
        do {
            System.out.println(string);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Erro! O campo não pode estar vazio. Por favor, digite um valor!");
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    formatter.parse(input);
                    if (validDateRegex(input)) {
                        invalidDate = false;
                    } else {
                        System.out.println("Data inválida! Digite no formato: dd/MM/yyyy");
                    }
                } catch (Exception e) {
                    System.out.println("Data inválida! Digite no formato: dd/MM/yyyy");
                }
            }
        } while (invalidDate);
        return input;
    }

    public Category isValidCategory(String string, Scanner scanner) {
        while (true) {
            try {
                String input = isValidString(string, scanner);
                return Category.isValidCategoria(input);
            } catch (IllegalArgumentException e) {
                System.out.println("""
                        Categoria inválida. Tente novamente!
                        As categorias válidas são:
                        Documentário
                        Série
                        Filme
                        Animação""");
            }
        }
    }
}
