package ui;

import model.Video;
import service.VideoService;
import strategy.SearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final VideoService videoService;
    private final SearchStrategy searchStrategy;

    public ConsoleUI(VideoService videoService, SearchStrategy searchStrategy) {
        this.videoService = videoService;
        this.searchStrategy = searchStrategy;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> adicionarVideo(scanner);
                case 2 -> listarVideos();
                case 3 -> pesquisarVideo(scanner);
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void adicionarVideo(Scanner scanner) {
        try {
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

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);

            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo: " + e.getMessage());
        }
    }

    private void listarVideos() {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            videos.forEach(System.out::println);
        }
    }

    private void pesquisarVideo(Scanner scanner) {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título informado.");
        } else {
            resultados.forEach(System.out::println);
        }
    }
}