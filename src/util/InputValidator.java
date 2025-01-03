package main;

import model.Categoria;

import java.util.Scanner;

public class InputValidator {

    public String getStringInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        return value;
    }

    public Categoria getCategoriaInput(String prompt, Scanner scanner) {
        while (true) {
            try {
                String input = getStringInput(prompt, scanner);
                return Categoria.isValidCategoria(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Tente novamente.");
            }
        }
    }
}
