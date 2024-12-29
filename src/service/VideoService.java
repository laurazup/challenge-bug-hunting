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
        listOfVideos = videoRepository.loadListOfVideos();
    }

    public void addVideo(Video video) {
        int indexOfVideo = listOfVideos.indexOf(video);

        if (indexOfVideo == -1) {
            listOfVideos.add(video);
            videoRepository.saveListOfVideos(listOfVideos);
        } else {
            System.err.println("O vídeo " + video.getTitle() + " já existe!");
        }
    }

    public List<Video> listVideos() {
        return videoRepository.findAll();
    }
}