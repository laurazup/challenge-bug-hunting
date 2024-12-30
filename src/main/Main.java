package main;

import service.MenuService;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService(".\\videos.csv");

        while (menuService.interactMenu()) {
            continue;
        }
    }
}