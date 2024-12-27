package service;

import model.Video;

import java.util.List;

public class VideoService {
    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public void addVideo(Video video) {
        repository.save(video);
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }
}