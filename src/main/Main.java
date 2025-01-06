package main;

import model.Video;
import repository.VideoRepositoryImpl;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import utills.Categoria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new VideoRepositoryImpl("videos.txt"));
        SearchStrategy searchStrategy = new SearchStrategyImpl();

        while (true) {

            int opcao = exibirMenu(scanner);
            switch (opcao) {

                case 1 -> adicionarVideo(scanner, videoService);
                case 2 -> listarVideos(videoService);
                case 3 -> pesquisarVideo(scanner, videoService, searchStrategy);
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }

        }
    }

    private static void adicionarVideo(Scanner scanner, VideoService videoService) {
        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite a categoria do vídeo (EDUCACAO,ENTRETENIMENTO,COMEDIA,AVENTURA,OUTRO): ");
        String categoriaStr = scanner.nextLine().toUpperCase();

        Categoria categoria;
        try {
            categoria = Categoria.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria inválida, digite uma disponivel");
            return;
        }

        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo: " + e.getMessage());
        }
    }

    private static void listarVideos(VideoService videoService) {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            for (Video video : videos) {
                System.out.println(video);
            }
        }
    }
    private static void pesquisarVideo(Scanner scanner, VideoService videoService, SearchStrategy searchStrategy) {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para a busca: " + query);
        } else {
            for (Video video : resultados) {
                System.out.println(video);
            }
        }
    }
    public static int exibirMenu(Scanner scanner){
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}

