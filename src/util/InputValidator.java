package util;

import model.Categoria;
import java.util.Scanner;

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
        int value;
        do {
            System.out.println(string);
            while (!scanner.hasNextInt()) {
                System.out.println("Erro! Digite um número válido!");
                scanner.nextLine();
            }
            value = scanner.nextInt();
            scanner.nextLine();
            if (value < 0) {
                System.out.println("Erro! O número não pode ser negativo. Digite um número válido!");
            }
        } while (value < 0);
        return value;
    }


    public Categoria getCategoriaInput(String string, Scanner scanner) {
        while (true) {
            try {
                String input = isValidString(string, scanner);
                return Categoria.isValidCategoria(input);
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
