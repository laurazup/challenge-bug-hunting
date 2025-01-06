package util;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class FileHandler {

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public List<Video> loadVideos() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Video video = new Video(data[0], data[1], Double.parseDouble(data[2]), data[3], data[4]);
                videos.add(video);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar vídeos: " + e.getMessage());
        }
        return videos;
    }

    public void saveVideos(List<Video> videos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Video video : videos) {
                writer.write(video.getTitle() + ";" + video.getDescription() + ";" + video.getDuration() + ";" + video.getCategory() + ";" + new SimpleDateFormat("dd/MM/yyyy").format(video.getPublicationDate()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vídeos: " + e.getMessage());
        }
    }

    public void deleteVideo(String title) {
        List<Video> videos = loadVideos();
        videos.removeIf(video -> video.getTitle().equalsIgnoreCase(title));
        saveVideos(videos);
    }
}
