package main;

import model.Video;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import util.Menu;
import model.Categoria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        boolean isMenuOpen = true;

        Menu menu = new Menu();

        while (isMenuOpen){
            menu.show();
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
                String cat = scanner.nextLine();
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                String dataStr = scanner.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataPublicacao = sdf.parse(dataStr);
                    Categoria categoria = Categoria.valueOf(cat.toUpperCase());
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