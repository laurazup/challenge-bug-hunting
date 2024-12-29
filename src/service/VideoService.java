package service;

import model.Video;
import repository.VideoRepository;

import java.util.ArrayList;
import java.util.Date;


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

    public void listVideos() {
        System.out.println("===== Lista de Vídeos =====");
        for (Video video : listOfVideos) {
            System.out.println(video.toString());
        }
        System.out.println();
    }

    public void searchVideosByTitle(String titleToSearch) {
        listOfVideos.stream()
                .filter((Video video) ->
                        video.getTitle().toUpperCase()
                                .contains(titleToSearch.toUpperCase()))
                .forEach(System.out::println);
    }

    public void editVideo(String titleOfVideo) {
        int indexOfVideo;
        Video oldVideo;
        Video newVideo;

        newVideo = new Video(titleOfVideo,
                "descrição",
                1,
                "Filme", new Date(System.currentTimeMillis()));

        indexOfVideo = listOfVideos.indexOf(newVideo);

        if(indexOfVideo != -1){
            //newVideo = Menu.addVideo();
            oldVideo = listOfVideos.get(indexOfVideo);
            oldVideo = newVideo;

            videoRepository.saveListOfVideos(listOfVideos);
        }
    }
}