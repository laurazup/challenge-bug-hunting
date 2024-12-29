package service;

import exception.InvalidVideoDescriptionException;
import exception.InvalidVideoDurationException;
import exception.InvalidVideoTitleException;
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
        if (video.getTitulo().isBlank()) {
            throw new InvalidVideoTitleException("O título do vídeo não pode estar vazio ou conter apenas espaços.");
        }
        if (video.getDescricao().isBlank()) {
            throw new InvalidVideoDescriptionException("A descrição do vídeo não pode estar vazia ou conter apenas espaços.");
        }
        if (video.getDuracao() < 1) {
            throw new InvalidVideoDurationException("Por favor, insira uma duração válida para o vídeo (maior que zero).");
        }
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }
}