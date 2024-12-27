package service;

import model.Video;
import repository.VideoRepository;

import java.util.List;

// - Associar add com Crate
// - Associar list com Read
// - Associar alter com Update
// - Associar erase com Delete
// - Associar relate com Report

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