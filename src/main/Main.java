package main;

import util.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isMenuOpen = true;

        Menu menu = new Menu();

        while (isMenuOpen) {
            menu.show();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                menu.addVideo();
            } else if (opcao == 2) {
               menu.listVideos();
            } else if (opcao == 3) {
               menu.searchVideos();
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}