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
    private File fileCSV = null;

    public VideoRepository(String filePathName) {
        try {
            fileCSV = new File(filePathName);
        } catch (Exception e) {
            System.out.println("Não foi possível associar o arquivo " + filePathName);
        }
    }

    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileCSV, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            // Ignorar erros por enquanto
        }
    }

    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileCSV))) {
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