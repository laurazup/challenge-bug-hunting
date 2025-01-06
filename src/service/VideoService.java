package service;

import model.Video;

import java.util.List;

public interface VideoService {
    static void adicionarVideo(Video video) {
    }

    void addVideo(Video video);
    List<Video> listVideos();
}
