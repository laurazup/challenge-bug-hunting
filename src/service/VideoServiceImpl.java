package service;

import exception.InvalidVideoDurationException;
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