package service;

import model.Video;
import repository.FileVideoRepository;
import strategy.SearchStrategy;
import java.util.List;
import java.util.stream.Collectors;

public class VideoServiceImpl implements VideoService {
    private final FileVideoRepository repository;

    public VideoServiceImpl(FileVideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        List<Video> videos = repository.loadVideos();
        videos.add(video);
        repository.saveVideos(videos);
    }

    @Override
    public void removeVideo(String title) {
        repository.deleteVideo(title);
    }

    @Override
    public void updateVideo(Video video) {
        List<Video> videos = repository.loadVideos();
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTitle().equalsIgnoreCase(video.getTitle())) {
                videos.set(i, video);
                break;
            }
        }
        repository.saveVideos(videos);
    }

    @Override
    public List<Video> getAllVideos() {
        return repository.loadVideos();
    }

    @Override
    public List<Video> getVideosByCategory(String category) {
        List<Video> videos = repository.loadVideos();
        return videos.stream()
                .filter(video -> video.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public Video getVideoByTitle(String title) {
        List<Video> videos = repository.loadVideos();
        return videos.stream()
                .filter(video -> video.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Video> searchVideos(SearchStrategy strategy, String criterion) {
        return strategy.search(repository.loadVideos(), criterion);  // Implementação correta com dois parâmetros
    }
}
