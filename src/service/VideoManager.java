package service;

import model.Video;
import repository.VideoRepository;
import strategy.SearchStrategy;
import java.util.List;

public class VideoManager {
    private final VideoRepository repository;

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
    }

    public void addVideo(Video video) {
        repository.save(video);
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }

    public List<Video> searchVideos(SearchStrategy strategy, String query) {
        return strategy.search(repository.findAll(), query);
    }

    public void deleteVideo(String title) {
        repository.deleteByTitle(title);
    }

    public void updateVideo(String tituloAntigo, Video videoAtualizado) {
        List<Video> videos = repository.findAll();

        // Verifica se o vídeo com o título antigo existe
        boolean videoEncontrado = false;
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            if (video.getTitulo().equalsIgnoreCase(tituloAntigo)) {
                // Substitui o vídeo antigo pelo atualizado
                videos.set(i, videoAtualizado);
                videoEncontrado = true;
                break;
            }
        }

        if (!videoEncontrado) {
            throw new IllegalArgumentException("Vídeo com o título '" + tituloAntigo + "' não encontrado.");
        }
        repository.saveAll(videos);
    }
}

