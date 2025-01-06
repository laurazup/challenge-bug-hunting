package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final File file;

    public FileVideoRepository(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                throw new RuntimeException("Não foi possivel criar o arquivo: " +filePath, e);
            }
        }
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o vídeo no arquivo: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video == null) {
                    System.err.println("Dado do video invalido" + line);
                } else {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o vídeo no arquivo: " + file.getAbsolutePath(), e);
        }
        return videos;
    }
}