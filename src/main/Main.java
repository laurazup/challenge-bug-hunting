package main;

import service.MenuService;

public class Main {
    public static void main(String[] args) {
        int quantityOfMenuInteraction = 0;
        MenuService menuService = new MenuService(".\\videos.csv");

        while (menuService.interactMenu()) {
            quantityOfMenuInteraction++;
        }

        System.out.println("Encerrando o Sistema de Gerenciamento de Vídeos após " +
                quantityOfMenuInteraction + " interações com o menu principal...");
    }
}