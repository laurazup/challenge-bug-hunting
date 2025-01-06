package service;

import model.Video;

import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import util.InputValidator;

import java.util.*;
import java.util.stream.Collectors;

public class VideoManager {
    private final Scanner scanner = new Scanner(System.in);
    private final VideoService videoService;

    public VideoManager() {
        this.videoService = new VideoServiceImpl(new repository.FileVideoRepository("videos.txt"));
    }

    public void start() {
        while (true) {
            printMenu();
            int option = readOption();

            switch (option) {
                case 1 -> addVideo();
                case 2 -> listVideos();
                case 3 -> searchVideos();  // Aqui a busca é feita diretamente pelo título
                case 4 -> editVideo();
                case 5 -> removeVideo();
                case 6 -> filterByCategory();
                case 7 -> sortByDate();
                case 8 -> generateReport();
                case 9 -> exit();
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Editar vídeo");
        System.out.println("5. Excluir vídeo");
        System.out.println("6. Filtrar vídeos por categoria");
        System.out.println("7. Ordenar vídeos por data de publicação");
        System.out.println("8. Exibir relatório de estatísticas");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int readOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void addVideo() {
        String title = InputValidator.readNonEmptyInput(scanner, "Digite o título do vídeo: ");
        String description = InputValidator.readNonEmptyInput(scanner, "Digite a descrição do vídeo: ");
        int duration = InputValidator.readPositiveNumber(scanner, "Digite a duração do vídeo (em minutos): ");

        String category = null;
        while (category == null || (!category.equalsIgnoreCase("Filme") && !category.equalsIgnoreCase("Série") && !category.equalsIgnoreCase("Documentário"))) {
            category = InputValidator.readNonEmptyInput(scanner, "Digite a categoria do vídeo (Filme, Série, Documentário): ");
            if (category == null || (!category.equalsIgnoreCase("Filme") && !category.equalsIgnoreCase("Série") && !category.equalsIgnoreCase("Documentário"))) {
                System.out.println("Categoria inválida. Tente novamente.");
            }
        }

        String publicationDate = InputValidator.readValidDate(scanner, "Digite a data de publicação (dd/MM/yyyy): ");

        Video video = new Video(title, description, duration, category, publicationDate);
        videoService.addVideo(video);
        System.out.println("Vídeo adicionado com sucesso!");
    }

    private void listVideos() {
        List<Video> videos = videoService.getAllVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo cadastrado.");
        } else {
            videos.forEach(System.out::println);
        }
    }

    private void searchVideos() {
        String title = InputValidator.readNonEmptyInput(scanner, "Digite o título do vídeo para busca: ");
        SearchStrategy titleSearchStrategy = new TitleSearchStrategy();

        // Buscar diretamente por título
        List<Video> results = videoService.searchVideos(titleSearchStrategy, title);

        if (results.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para o título: " + title);
        } else {
            results.forEach(System.out::println);
        }
    }

    private void editVideo() {
        String title = InputValidator.readNonEmptyInput(scanner, "Digite o título do vídeo a editar: ");
        Video videoToEdit = videoService.getVideoByTitle(title);

        if (videoToEdit != null) {
            System.out.println("Editando o vídeo: " + videoToEdit.getTitle());
            String newTitle = InputValidator.readNonEmptyInput(scanner, "Novo título: ");
            String newDescription = InputValidator.readNonEmptyInput(scanner, "Nova descrição: ");
            int newDuration = InputValidator.readPositiveNumber(scanner, "Nova duração (em minutos): ");

            // Garantir que a categoria seja válida
            String newCategory;
            while (true) {
                newCategory = InputValidator.readNonEmptyInput(scanner, "Nova categoria (Filme, Série, Documentário): ");
                if (newCategory.equalsIgnoreCase("Filme") || newCategory.equalsIgnoreCase("Série") || newCategory.equalsIgnoreCase("Documentário")) {
                    break; // Categoria válida, sai do laço
                } else {
                    System.out.println("Categoria inválida. Tente novamente.");
                }
            }

            String newPublicationDate = InputValidator.readValidDate(scanner, "Nova data de publicação (dd/MM/yyyy): ");

            videoToEdit.setTitle(newTitle);
            videoToEdit.setDescription(newDescription);
            videoToEdit.setDuration(newDuration);
            videoToEdit.setCategory(newCategory);  // Categoria agora é validada
            videoToEdit.setPublicationDate(newPublicationDate);

            videoService.updateVideo(videoToEdit);
            System.out.println("Vídeo editado com sucesso!");
        } else {
            System.out.println("Vídeo não encontrado.");
        }
    }

    private void removeVideo() {
        String title = InputValidator.readNonEmptyInput(scanner, "Digite o título do vídeo a excluir: ");
        videoService.removeVideo(title);
        System.out.println("Vídeo removido com sucesso (se existia).");
    }

    private void filterByCategory() {
        String category = InputValidator.readNonEmptyInput(scanner, "Digite a categoria para filtrar (Filme, Série, Documentário): ");
        List<Video> filteredVideos = videoService.getVideosByCategory(category);

        if (filteredVideos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para a categoria: " + category);
        } else {
            filteredVideos.forEach(System.out::println);
        }
    }

    private void sortByDate() {
        List<Video> sortedVideos = videoService.getAllVideos().stream()
                .sorted(Comparator.comparing(Video::getPublicationDate))
                .collect(Collectors.toList());

        if (sortedVideos.isEmpty()) {
            System.out.println("Nenhum vídeo para ordenar.");
        } else {
            sortedVideos.forEach(System.out::println);
        }
    }

    private void generateReport() {
        int totalVideos = videoService.getAllVideos().size();
        double totalDuration = videoService.getAllVideos().stream().mapToDouble(Video::getDuration).sum();
        Map<String, Long> categoryCount = new HashMap<>();

        for (Video video : videoService.getAllVideos()) {
            categoryCount.put(video.getCategory(), categoryCount.getOrDefault(video.getCategory(), 0L) + 1);
        }

        System.out.println("\nRelatório de Estatísticas:");
        System.out.println("Número total de vídeos: " + totalVideos);
        System.out.println("Duração total dos vídeos: " + totalDuration + " minutos");
        System.out.println("Quantidade de vídeos por categoria: " + categoryCount);
    }

    private void exit() {
        System.out.println("Saindo do sistema...");
        scanner.close();
        System.exit(0);
    }
}
