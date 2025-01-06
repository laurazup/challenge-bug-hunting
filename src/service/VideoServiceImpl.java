package service;

import model.Video;
import repository.VideoRepository;
import strategy.SearchStrategy;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;
    private final SearchStrategy searchStrategy;
    public VideoServiceImpl(VideoRepository repository, SearchStrategy searchStrategy) {
        this.repository = repository;
        this.searchStrategy = searchStrategy;
    }

private void adicionarVideo(Video video){
    if (video == null){
        throw new IllegalArgumentException("Desculpe, mas o vídeo não pode ser nulo.");
    }
    if (video.getTitulo() == null || video.getTitulo().isEmpty()){
        throw new IllegalArgumentException("Desculpe, o título do vídeo não pode ser nulo.");
    }
    if (video.getDescricao() == null || video.getDescricao().isEmpty()){
        throw new IllegalArgumentException("Por favor, a descrição do vídeo não pode ser vazia.");
    }

}
    @Override
    public void addVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("O vídeo não pode ser nulo.");
        }
        if (video.getTitulo() == null || video.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título do vídeo não pode ser vazio.");
        }
        if (video.getDescricao() == null || video.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição do vídeo não pode ser vazia.");
        }
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
    return repository.findAll();
    }
}