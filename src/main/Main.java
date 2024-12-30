package main;

import model.CategoryType;
import model.MenuType;
import model.Video;
import service.MenuService;
import service.VideoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService(".\\videos.csv");
        Scanner scanner = new Scanner(System.in);

        // - Revover VideoService da classe Main (testes)
        VideoService videoService = new VideoService(".\\videos.csv");

        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        while(menuService.interactMenu()){
            continue;
        }

        while (true) {
            MenuType.showMenu();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                System.out.print("Digite o título do vídeo: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a descrição do vídeo: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite a duração do vídeo (em minutos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Digite a categoria do vídeo: ");
                int categoria = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                String dataStr = scanner.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataPublicacao = sdf.parse(dataStr);
                    Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                    videoService.addVideo(video);
                    System.out.println("Vídeo adicionado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar vídeo.");
                }
            } else if (opcao == 2) {
                videoService.listVideos();
            } else if (opcao == 3) {
                System.out.print("Digite o título para busca: ");
                String query = scanner.nextLine();
                // Adicionar filtro na classe VideoService
                // List<Video> resultados = searchStrategy.search(videoService.listVideos1(), query);
                // for (Video video : resultados) {
                //     System.out.println(video);
                // }
                videoService.listVideos();
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
                // Testes
                videoService.listVideos();
                videoService.searchVideosByTitle("Matrix");
                videoService.editVideo("Matrix");
                videoService.removeVideo("Matrix");
                videoService.filterVideoByCategory(CategoryType.MOVIE);
                videoService.sortVideoByDate();
            }
        }

        scanner.close();
    }
//    public static void main(String[] args) {
//        MenuService menuService = new MenuService();
//        while (menuService.interactMenu()){
//            continue;
//        }
//    }
}