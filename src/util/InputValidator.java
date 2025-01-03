package util;

import model.Categoria;
import java.util.Scanner;

public class InputValidator {

    public String isValidString(String string, Scanner scanner) {
        System.out.print(string);
        return scanner.nextLine();
    }

    public int isValidInt(String string, Scanner scanner) {
        System.out.print(string);
        while (!scanner.hasNextInt()) {
            System.out.println("Erro! Por favor, digite um número!");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
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
