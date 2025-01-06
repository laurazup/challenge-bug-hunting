package strategy;

import model.Video;
import java.util.List;

public interface SearchStrategy {
    List<Video> search(List<Video> videos, String criterion);  // Método que requer dois parâmetros
}
