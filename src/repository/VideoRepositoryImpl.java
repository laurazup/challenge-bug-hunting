package repository;

import model.Video;
import utills.VideoConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VideoRepositoryImpl implements VideoRepository {
    private final File file;

    public VideoRepositoryImpl(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(VideoConverter.toString(video));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("erro ao salvar o vídeo no arquivo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = VideoConverter.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }
        return videos;
    }
}