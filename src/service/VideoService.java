package service;

import model.VideoModel;

import java.util.List;
import java.util.Map;

public interface VideoService {
    void addVideo(VideoModel video);
    List<VideoModel> listVideos();
    List<VideoModel> searchByTitle(String title);
    boolean editVideo(String title, VideoModel updatedVideo);
    boolean deleteVideo(String title);
    List<VideoModel> filterVideosByCategory(String category);
    List<VideoModel> sortVideosByPublicationDate(boolean reverse);
    Map<String, Object> generateStatistics();
}

