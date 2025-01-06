package repository;

import model.Video;
import util.FileHandler;

import java.io.IOException;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final String filePath;

    public FileVideoRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Video video) {
        try {
            FileHandler.writeLine(filePath, video.toString(), true);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o vídeo: " + e.getMessage());
        }
    }

    @Override
    public List<Video> findAll() {
        try {
            return FileHandler.readLines(filePath).stream()
                    .map(Video::fromString)
                    .toList();
        } catch (IOException e) {
            System.err.println("Erro ao ler os vídeos: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void saveAll(List<Video> videos) {
        try {
            for (Video video : videos) {
                FileHandler.writeLine(filePath, video.toString(), false);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os vídeos: " + e.getMessage());
        }
    }
}