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
    private final ArrayList<Video> listOfVideos;
    private final VideoRepository videoRepository;

    public VideoService(String filePathName) {
        videoRepository = new VideoRepository(filePathName);
        listOfVideos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        listOfVideos.add(video);
        videoRepository.save(listOfVideos);
    }

    public List<Video> listVideos() {
        return videoRepository.findAll();
    }
}