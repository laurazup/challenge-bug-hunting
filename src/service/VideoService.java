package service;

import model.Video;
import repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

// - Associar add com Crate
// - Associar list com Read
// - Associar alter com Update
// - Associar erase com Delete
// - Associar relate com Report

public class VideoService {
    private final VideoRepository repository;
    private final ArrayList<Video> listOfVideos;

    // Checkar o parâmetro repository se é necessário.
    public VideoService(VideoRepository repository) {

        this.repository = repository;
        listOfVideos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        listOfVideos.add(video);
        repository.save(listOfVideos);
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }
}