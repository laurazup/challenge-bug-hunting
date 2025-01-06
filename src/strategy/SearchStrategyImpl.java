package strategy;

import model.Video;
import model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class SearchStrategyImpl implements SearchStrategy {
    @Override
    public List<Video> search(List<Video> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitulo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public class CategorySearchStrategy implements SearchStrategy {
        @Override
        public List<Video> search(List<Video> videos, String query) {
            Categoria categoria = Categoria.valueOf(query.toUpperCase());
            return videos.stream()
                    .filter(video -> video.getCategoria() == categoria).
                    collect(Collectors.toList());
        }
    }
}