package service;

import model.CategoryType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidateService {
    private static Scanner scanner;

    public ValidateService() {
        scanner = new Scanner(System.in);
    }

    public int validateMenu(int rangeOfOptions, int exitOrdinal) {
        boolean isNotValid = true;
        int chosenOption = exitOrdinal;

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

        return chosenOption;
    }

    public String validateText() {
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

    public String validateTitle(){
        System.out.print("Digite o título do vídeo(sem ';'): ");
        return validateText();
    }

    public String validateDescription(){
        System.out.print("Digite a descrição do vídeo (sem ';'): ");
        return validateText();
    }

    public int validateDurationInMinutes() {
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

    public int validateCategory(int rangeOfOptions) {
        boolean isNotValid = true;
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

        return chosenOption;
    }

    public Date validatePublicationDate() {
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

    public void close() {
        scanner.close();
    }
}
