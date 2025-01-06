package strategy;

import model.Video;
import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchStrategy implements SearchStrategy {
    @Override
    public List<Video> search(List<Video> videos, String criterion) {
        return videos.stream()
                .filter(video -> video.getCategory().equalsIgnoreCase(criterion))  // Filtra por categoria
                .collect(Collectors.toList());
    }
}
