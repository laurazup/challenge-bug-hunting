package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final File file;

    public FileVideoRepository(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o vídeo no arquivo: " + e.getMessage());
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        if (!file.exists()) {
            return videos; // Retorna lista vazia se o arquivo não existir
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                videos.add(Video.fromString(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler os vídeos do arquivo: " + e.getMessage());
        }
        return videos;
    }

    /**
     * Salva uma lista completa de vídeos no arquivo, sobrescrevendo o conteúdo existente.
     *
     * @param videos Lista de vídeos a ser salva.
     */
    public void saveAll(List<Video> videos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                bw.write(video.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a lista de vídeos no arquivo: " + e.getMessage());
        }
    }
}