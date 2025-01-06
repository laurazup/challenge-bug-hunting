package repository;

import model.Video;

import java.util.List;

public interface VideoRepository {
    void save(Video video);
    List<Video> findAll();
}
