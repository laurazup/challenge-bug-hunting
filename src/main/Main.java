package main;

import model.Video;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        ConsoleUI consoleUI = new ConsoleUI(videoService, searchStrategy);

        consoleUI.start();
    }
}