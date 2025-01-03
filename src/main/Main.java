package main;

import userInterface.FileHandler;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoManager;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import model.VideoModel;

import java.util.Map.Entry;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FileVideoRepository repository = new FileVideoRepository("video.txt");
        VideoManager videoManager = new VideoManager(repository);
        FileHandler fileHandler = new FileHandler(videoManager);
        VideoService videoService;
        SearchStrategy searchStrategy;

        videoService = new VideoManager(new FileVideoRepository("video.txt"));
        searchStrategy = new TitleSearchStrategy();

        while (true) {
            int option = fileHandler.displayMenu();
            switch (option) {
                case 1 -> handleAddVideo(fileHandler, videoService);
                case 2 -> handleListVideos(videoService);
                case 3 -> handleSearchByTitle(fileHandler, videoService, searchStrategy);
                case 4 -> handleEditVideo(fileHandler, videoService);
                case 5 -> handleDeleteVideo(fileHandler, videoService);
                case 6 -> handleFilterByCategory(fileHandler, videoService);
                case 7 -> handleSortByPublicationDate(fileHandler, videoService);
                case 8 -> handleStatisticsReport(videoService);
                case 9 -> {
                    fileHandler.closeScanner();
                    System.out.println("Saindo do sistema...  Até logo!");
                    return;
                }
                default -> System.out.println("Opção inválida. Por favor, escolha uma opção valida. ");
            }
        }

    }

    public static void handleAddVideo(FileHandler fileHandler, VideoService videoService) {
        VideoModel video = fileHandler.captureVideo();
        if (video != null) {
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        }
    }

    private static void handleListVideos(VideoService videoService) {
        System.out.println("=== Lista de Vídeos ===");
        List<VideoModel> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            System.out.println("\n=== Lista de videos ===");
            videos.forEach(video -> System.out.println(video));
        }
    }

    private static void handleSearchByTitle(FileHandler fileHandler, VideoService videoService, SearchStrategy searchStrategy) {
        String query = fileHandler.prompt("Digite o título para busca: ");
        List<VideoModel> results = searchStrategy.search(videoService.listVideos(), query);
        if (results.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título: " + query);
        } else {
            System.out.println("\n===Resultados da Busca ===");
            results.forEach(video -> System.out.println(video));
        }
    }

    private static void handleEditVideo(FileHandler fileHandler, VideoService videoService) {
        String title = fileHandler.prompt("Digite o título do video a ser editado");
        VideoModel updatedVideo = fileHandler.captureVideo();
        if (updatedVideo != null) {
            boolean success = videoService.editVideo(title, updatedVideo);
            if (success) {
                System.out.println("Vídeo editado com sucesso!");
            } else {
                System.out.println("Vídeo não encontrado.");
            }
        } else {
            System.out.println("Erro ao carregar dados para edição.");
        }
    }

    private static void handleDeleteVideo(FileHandler fileHandler, VideoService videoService) {
        String title = fileHandler.prompt("Digite o título do vídeo a ser excluído: ");
        boolean success = videoService.deleteVideo(title);
        if (success) {
            System.out.println("Vídeo excluído com sucesso!");
        } else {
            System.out.println("Vídeo não encontrado.");
        }
    }

    private static void handleFilterByCategory(FileHandler fileHandler, VideoService videoService) {
        String category = fileHandler.prompt("Digite a categoria para filtrar: ");
        List<VideoModel> filteredVideos = videoService.filterVideosByCategory(category);
        if (filteredVideos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para a categoria: " + category);
        } else {
            System.out.println("=== Vídeos na categoria: " + category + " ===");
            filteredVideos.forEach(video -> System.out.println(video));
        }
    }

    private static void handleSortByPublicationDate(FileHandler fileHandler, VideoService videoService) {
        System.out.println("=== Ordenar vídeos por data de publicação ===");
        boolean reverse = fileHandler.confirm("Deseja ordenar em ordem reversa? (sim/não): ");
        List<VideoModel> sortedVideos = videoService.sortVideosByPublicationDate(reverse);
        if (sortedVideos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para ser organizado.");
        } else {
            System.out.println("\n=== Vídeos Ordenados por Data ===");
            sortedVideos.forEach(video -> System.out.println(video));
        }
    }

    private static void handleStatisticsReport(VideoService videoService) {
        Map<String, Object> stats = videoService.generateStatistics();
        System.out.println("=== Relatório de Estatísticas ===");
        System.out.println("Total de vídeos: " + stats.get("Total de videos"));
        System.out.println("Duração total: " + stats.get("Duração total") + " minutos");
        System.out.println("Vídeos por categoria:");
        ((Map<String, Long>) stats.get("Videos por categoria")).forEach((category, count) -> System.out.println("- " + category + ": " + count + " vídeo(s)"));
    }
}
