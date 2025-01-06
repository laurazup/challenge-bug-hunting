package service;

import model.Categoria;
import model.Video;
import java.util.List;

public class VideoManager {

    private final VideoService videoService;

    public VideoManager(VideoService videoService) {
        this.videoService = videoService;
    }

    public void adicionarVideo(String titulo, String descricao, int duracao, Categoria categoria, String dataPublicacao) {
        videoService.addVideo(new Video(titulo, descricao, duracao, categoria, dataPublicacao));
    }

    public List<Video> listarVideos(){
        return videoService.listVideos();
    }
}
