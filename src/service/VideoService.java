package service;

import model.Video;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    List<Video> listVideos();
    void updateVideo(Video updatedVideo, Video videoAtualizado);
    void salvarVideosNoArquivo(List<Video> videos) throws IOException;
    List<Video> searchVideos(String query);
    List<Video> ordenarPorDataPublicacao(List<Video> videos);

    List<Video> listVideosOrderedByDate();

    void updateVideoList(List<Video> videos);
}