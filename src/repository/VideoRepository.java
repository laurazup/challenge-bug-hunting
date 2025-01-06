package repository;

import model.Video;
import java.util.List;

public interface VideoRepository {
    void save(Video video);
    List<Video> findAll();
    void clear();
    void saveAll(List<Video> videos); // Método para salvar uma lista de vídeos.
    void deleteByTitle(String title);

}