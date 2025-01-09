package strategy;

import model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    @Override
    public List<Video> search(List<Video> videos, String query) {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("A consulta de busca não pode ser vazia.");
        }
        return videos.stream()
                .filter(video -> video.getTitulo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}