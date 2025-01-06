package Statistic;

import model.Video;
import model.VideoManager;
import repository.FileVideoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    public static void generateStatistics() {
        VideoManager videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        List<Video> videos = videoService.listVideos();

        int totalVideos = videos.size();
        int totalDuration = videos.stream().mapToInt(Video::getDuracao).sum();

        Map<String, Integer> videosByCategory = new HashMap<>();
        for (Video video : videos) {
            String category = video.getCategoria();
            videosByCategory.put(category, videosByCategory.getOrDefault(category, 0) + 1);
        }

        System.out.println("Relatório de Estatísticas:");
        System.out.println("Número total de vídeos: " + totalVideos);
        System.out.println("Duração total de todos os vídeos: " + totalDuration + " minutos");
        System.out.println("Quantidade de vídeos por categoria:");
        for (Map.Entry<String, Integer> entry : videosByCategory.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " vídeos");
        }
    }
}

