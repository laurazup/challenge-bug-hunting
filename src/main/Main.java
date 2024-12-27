package main;

import model.MenuType;
import model.Video;
import repository.VideoRepository;
import service.VideoService;
import service.SearchVideoByTitleService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoService(new VideoRepository("videos.txt"));
        SearchVideoByTitleService searchStrategy = new SearchVideoByTitleService();

        /*
        while(MenuService.interact()){
        }
         */
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
                String categoria = scanner.nextLine();
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
                List<Video> videos = videoService.listVideos();
                for (Video video : videos) {
                    System.out.println(video);
                }
            } else if (opcao == 3) {
                System.out.print("Digite o título para busca: ");
                String query = scanner.nextLine();
                List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
                for (Video video : resultados) {
                    System.out.println(video);
                }
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