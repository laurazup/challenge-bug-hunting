package repository;

import model.VideoModel;
import java.util.List;


public interface VideoRepository {
    void save(VideoModel video);
    List<VideoModel> findAll();
    void saveAll(List<VideoModel> videos);
    void deletedByTitle(String title);

}