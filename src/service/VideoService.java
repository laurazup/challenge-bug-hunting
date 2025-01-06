package service;

import model.Video;
import strategy.SearchStrategy;
import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    void removeVideo(String title);
    void updateVideo(Video video);
    List<Video> getAllVideos();
    List<Video> getVideosByCategory(String category);
    Video getVideoByTitle(String title);

    List<Video> searchVideos(SearchStrategy strategy, String criterion);  // Este é o único necessário
}
