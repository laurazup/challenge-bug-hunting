package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// - Validar Create
// - Validar Read
// - Criar Update
// - Criar Delete
// - Criar Report
// - Retornar String em vez de Video (toString( ))

public class VideoRepository {
    private final File file;

    public VideoRepository(String filePath) {
        this.file = new File(filePath);
    }

    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            // Ignorar erros por enquanto
        }
    }

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
            // Ignorar erros por enquanto
        }
        return videos;
    }
}