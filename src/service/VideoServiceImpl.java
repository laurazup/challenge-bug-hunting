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
        if (video == null) {
            throw new IllegalArgumentException("O vídeo não pode ser nulo");
        }
        if (video.getTitulo() == null || video.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título do vídeo é obrigatório");
        }
        if (video.getDuracao() <= 0) {
            throw new IllegalArgumentException("A duração do vídeo deve ser maior que zero");
        }
        try {
            repository.save(video);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o vídeo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }
}