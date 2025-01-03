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
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            bw.write( video.getTitulo() + ";" + video.getDescricao() + ";" +
                    video.getDuracao() + ";" + video.getCategoria() + ";" + video.getDataPublicacao());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Algo deu errado! O vídeo não foi salvo!" + e.getMessage());
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
            System.out.println("Erro! Não foi possível listar os vídeos salvos" + e.getMessage());
        }
        return videos;
    }

    @Override
    public void deleteByTitle(String title) {
        List<Video> videos = findAll();
        boolean videoFound = false;

        List<Video> updatedVideos = new ArrayList<>();
        for (Video video : videos) {
            if (!video.getTitulo().equalsIgnoreCase(title)) {
                updatedVideos.add(video);
            } else {
                videoFound = true;
            }
        }

        if (!videoFound) {
            System.out.println("Vídeo com o título '" + title + "' não encontrado.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : updatedVideos) {
                bw.write(video.getTitulo() + ";" + video.getDescricao() + ";" +
                        video.getDuracao() + ";" + video.getCategoria() + ";" + video.getDataPublicacao());
                bw.newLine();
            }
            System.out.println("Vídeo com o título '" + title + "' foi deletado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao deletar o vídeo: " + e.getMessage());
        }
    }
}

