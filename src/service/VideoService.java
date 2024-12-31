package service;

import model.Video;
import repository.VideoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

// - Exibir relatório de estatísticas
// - Refatorar editVideo

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

    public int editVideo(String titleOfVideo) {
        int indexOfVideo;
        Video chosenVideo;
        Video stubVideo;

        stubVideo = new Video(
                titleOfVideo,
                "descrição",
                1,
                0,
                new Date(System.currentTimeMillis())
        );

        indexOfVideo = listOfVideos.indexOf(stubVideo);

        if (indexOfVideo != -1) {
            chosenVideo = listOfVideos.get(indexOfVideo);
            System.out.println("Video encontrado");
            System.out.println(chosenVideo);
        } else {
            System.err.println("Vídeo não encontrado para edição!");
        }

        return indexOfVideo;
    }

    public void editVideoTitle(String newTitle, int indexOfVideo){
        Video editVideo = listOfVideos.get(indexOfVideo);
        editVideo.setTitle(newTitle);
    }

    public void removeVideo(String titleOfVideo) {
        int indexOfVideo = listOfVideos.indexOf(new Video(
                titleOfVideo,
                "descrição",
                1,
                0,
                new Date(System.currentTimeMillis())));

        if (indexOfVideo != -1) {
            listOfVideos.remove(indexOfVideo);
            videoRepository.saveListOfVideos(listOfVideos);
            System.out.println("Video " + titleOfVideo + " excluído com sucesso!");
        } else {
            System.err.println("Vídeo com o título " + titleOfVideo + " não encontrado!");
        }
    }

    public void filterVideosByCategory(int categoryOrdinal) {
        Video stubVideo = new Video(
                "Titulo",
                "Descrição",
                1,
                categoryOrdinal,
                new Date(System.currentTimeMillis())
        );
        listOfVideos.stream()
                .filter((Video video) ->
                        video.getCategory().equals(stubVideo.getCategory()))
                .forEach(System.out::println);
    }

    public void sortVideoByDate() {
        listOfVideos.stream()
                .sorted(Comparator.comparing(
                        Video::getPublicationDate))
                .forEach(System.out::println);
    }
}