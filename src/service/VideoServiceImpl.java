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
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    @Override
    public void updateVideo(String title, Video updatedVideo) {
        List<Video> videos = repository.findAll();
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTitulo().equalsIgnoreCase(title)) {
                videos.set(i, updatedVideo);
                repository.saveAll(videos);
                return;
            }
        }
        System.out.println("Erro: Nenhum vídeo encontrado com o título fornecido.");
    }
}