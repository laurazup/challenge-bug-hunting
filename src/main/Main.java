package main;

import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import util.UserInterface;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        UserInterface ui = new UserInterface(videoService, searchStrategy);

        ui.start();
    }
}