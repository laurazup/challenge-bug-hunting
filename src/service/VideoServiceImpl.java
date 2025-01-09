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
            throw new IllegalArgumentException("O vídeo não pode ser nulo.");
        }
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    @Override
    public void updateVideo(int index, Video video) {
        List<Video> videos = repository.findAll();
        if (index < 0 || index >= videos.size()) {
            throw new IllegalArgumentException("Índice inválido.");
        }
        videos.set(index, video);
        repository.saveAll(videos);
    }

    @Override
    public void deleteVideo(int index) {
        List<Video> videos = repository.findAll();
        if (index < 0 || index >= videos.size()) {
            throw new IllegalArgumentException("Índice inválido.");
        }
        videos.remove(index);
        repository.saveAll(videos);
    }
}