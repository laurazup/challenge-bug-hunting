package strategy;

import model.VideoModel;

import java.util.List;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    public List<VideoModel> search(List<VideoModel> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitle().toUpperCase().contains(query.toUpperCase()))
                .collect(Collectors.toList());
    }
}