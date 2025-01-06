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
            System.out.println("Erro ao salvar o vídeo: " + e.getMessage());
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
            System.out.println("Erro ao ler o vídeo: " + e.getMessage());
        }
        return videos;
    }

    @Override
    public void update(Video updatedVideo, String tituloOriginal) {
        List<Video> videos = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                if (video.getTitulo().equals(tituloOriginal)) {
                    bw.write(updatedVideo.toCSV());
                } else {
                    bw.write(video.toCSV());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o vídeo: " + e.getMessage());
        }
    }

    public void saveAll(List<Video> videos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                writer.write(video.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os vídeos no arquivo.", e);
        }
    }

    /**
     * Retorna todos os vídeos organizados por data de publicação.
     *
     * @return Lista de vídeos ordenada por data de publicação.
     */
    public List<Video> findAllOrderedByDate() {
        List<Video> videos = findAll();
        return Video.organizarPorDataPublicacao(videos);
    }
}
