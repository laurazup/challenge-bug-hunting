package main;

import service.VideoManager;

public class Main {
    public static void main(String[] args) {
        VideoManager videoManager = new VideoManager();
        videoManager.start();
    }
}