package util;

import model.Video;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
    SearchStrategy searchStrategy = new TitleSearchStrategy();

    public void show() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void addVideo() {
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
            System.out.println(e.getMessage());
        }
    }

    public void listVideos() {
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    public void searchVideos() {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        for (Video video : resultados) {
            System.out.println(video);
        }
    }
}
