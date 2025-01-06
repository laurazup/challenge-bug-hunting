package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InputValidator {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String readNonEmptyInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Este campo não pode ficar vazio. Por favor, insira um valor válido.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static int readPositiveNumber(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("O número deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }
    }

    // Método atualizado para validar a data
    public static String readValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String date = scanner.nextLine().trim();

            // Verifica se a data tem o formato dd/MM/yyyy com as barras
            if (date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                try {
                    // Verifica se a data é válida
                    sdf.setLenient(false);  // Desativa a análise "leniente" (ex.: 32/13/2024 será rejeitada)
                    sdf.parse(date);  // Tenta analisar a data
                    return date;  // Se a data for válida, retorna no formato dd/MM/yyyy
                } catch (ParseException e) {
                    System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                }
            } else {
                System.out.println("Formato inválido. Use exatamente o formato dd/MM/yyyy.");
            }
        }
    }

    public static java.util.Date parseDate(String date) {
        try {
            return sdf.parse(date);  // Converte para java.util.Date
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao interpretar a data.");
        }
    }
}
