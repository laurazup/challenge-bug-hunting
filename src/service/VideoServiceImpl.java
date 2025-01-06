package service;

import model.Video;
import repository.VideoRepository;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;

    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        validateVideo(video); // Validações para um metodo
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    // Metodo privado para validações
    private void validateVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("O vídeo não pode ser nulo.");
        }
        if (video.getTitulo() == null || video.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título do vídeo não pode ser nulo ou vazio.");
        }
        if (video.getDescricao() == null || video.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição do vídeo não pode ser nula ou vazia.");
        }
        if (video.getDuracao() <= 0) {
            throw new IllegalArgumentException("A duração do vídeo deve ser maior que zero.");
        }
    }
}