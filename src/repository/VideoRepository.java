package repository;

import model.Video;

import java.util.List;

public interface VideoRepository {

    void save(Video video);

    List<Video> findAll();

    void update(Video updatedVideo, String tituloOriginal);

    void saveAll(List<Video> videos);
}