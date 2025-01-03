package util;

import model.Categoria;
import model.Video;
import service.VideoService;
import strategy.SearchStrategy;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final VideoService videoService;
    private final SearchStrategy searchStrategy;
    private final InputValidator inputValidator;
    private final OutputFormatter outputFormatter;
    private final Scanner scanner;

    public UserInterface(VideoService videoService, SearchStrategy searchStrategy) {
        this.videoService = videoService;
        this.searchStrategy = searchStrategy;
        this.inputValidator = new InputValidator();
        this.outputFormatter = new OutputFormatter();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int option = inputValidator.isValidInt("Escolha uma opção: ", scanner);
            running = handleMenuOption(option);
        }

    }

    private void printMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Sair");
    }

    private boolean handleMenuOption(int option) {
        switch (option) {
            case 1 -> addVideo();
            case 2 -> listVideos();
            case 3 -> searchVideos();
            case 4 -> {
                System.out.println("Saindo do sistema...");
                return false;
            }
            default -> {
                System.out.println("""
                        

                        """);
                System.out.println("Opção inválida!\n" +
                "Digite 1, 2, 3 ou 4");
            }
        }
        return true;
    }

    private void addVideo() {
        try {
            String titulo = inputValidator.isValidString("Digite o título do vídeo: ", scanner);
            String descricao = inputValidator.isValidString("Digite a descrição do vídeo: ", scanner);
            int duracao = inputValidator.isValidInt("Digite a duração do vídeo (em minutos): ", scanner);
            Categoria categoria = inputValidator.getCategoriaInput("Digite a categoria do vídeo: ", scanner);
            String dataStr = inputValidator.isValidString("Digite a data de publicação (dd/MM/yyyy): ", scanner);

            Video video = new Video(titulo, descricao, duracao, categoria, dataStr);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o vídeo: " + e.getMessage());
        }
    }

    private void listVideos() {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo foi encontrado!");
        } else {
            videos.forEach(outputFormatter::printVideo);
        }
    }

    private void searchVideos() {
        String query = inputValidator.isValidString("Título do vídeo que deseja buscar: ", scanner);
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo foi encontrado com esse título." +
                    "Verifique o que você digitou!");
        } else {
            resultados.forEach(outputFormatter::printVideo);
        }
    }
}