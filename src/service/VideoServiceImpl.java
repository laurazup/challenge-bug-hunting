package service;

import model.Video;
import repository.FileVideoRepository;
import repository.VideoRepository;

import java.io.IOException;
import java.util.List;

public class VideoServiceImpl implements VideoService {

    private VideoRepository repository;

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
    public void updateVideo(Video videoOriginal, Video videoAtualizado) {
        repository.update(videoAtualizado, videoOriginal.getTitulo());

    }

    @Override
    public void salvarVideosNoArquivo(List<Video> videos) throws IOException {

    }

    @Override
    public List<Video> searchVideos(String query) {
        return List.of();
    }

    @Override
    public List<Video> ordenarPorDataPublicacao(List<Video> videos) {
        return List.of();
    }

    @Override
    public void updateVideoList(List<Video> videos) {
        repository.saveAll(videos);
    }

    @Override
    public List<Video> listVideosOrderedByDate() {
        if (repository instanceof FileVideoRepository) {
            return ((FileVideoRepository) repository).findAllOrderedByDate();
        }
        throw new UnsupportedOperationException("Repositório não suporta ordenação por data.");
    }
}