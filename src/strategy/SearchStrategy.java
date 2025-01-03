package strategy;

import model.VideoModel;

import java.util.List;

public interface SearchStrategy {
    List<VideoModel> search(List<VideoModel> videos, String query);
}