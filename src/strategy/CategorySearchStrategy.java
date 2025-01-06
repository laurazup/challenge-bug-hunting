package strategy;

import model.Video;
import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchStrategy implements SearchStrategy{

    @Override
    public List<Video> search(List<Video> videos, String query) {
        String queyUpperCase = query.toUpperCase();

        return videos.stream().filter(video -> video.getCategoria().name().equalsIgnoreCase(queyUpperCase))
                .collect(Collectors.toList());
    }
}
