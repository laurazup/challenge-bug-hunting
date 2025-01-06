package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepositoryImpl implements VideoRepository {
    private final File file;

    public FileVideoRepositoryImpl(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        if (video == null){
            throw new IllegalArgumentException("O vídeo não pode ser nulo.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o vídeo");
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o vídeo.");
        }
        return videos;
    }
}
