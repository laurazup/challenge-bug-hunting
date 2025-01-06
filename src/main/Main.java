package main;

import Statistic.Statistics;
import menu.Menu;
import model.FileHandler;
import model.VideoManager;
import repository.FileVideoRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler videoService = new FileHandler(new FileVideoRepository("videos.txt"));
        VideoManager videoManager = new VideoManager(new FileVideoRepository("videos.txt"));

        boolean isMenuOpen = true;

        while (isMenuOpen) {
            Menu.show();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                videoService.saveVideo();
            } else if (opcao == 2) {
                videoService.listingVideos();
            } else if (opcao == 3) {
                videoManager.searchByTitle();
            } else if (opcao == 4) {
                videoService.editVideo();
            } else if (opcao == 5) {
                videoService.deleteVideo();
            } else if (opcao == 6) {
                videoManager.searchByCategory();
            } else if (opcao == 7) {
                videoManager.SortListByDate();
            } else if (opcao == 8) {
                Statistics.generateStatistics();
            } else if (opcao == 9) {
                System.out.println("Saindo do sistema...");
                isMenuOpen = false;
            } else {
                System.out.println("Opção inválida. Digite uma opção do menu!");
            }
        }
        scanner.close();
    }
}