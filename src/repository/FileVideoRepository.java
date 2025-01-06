package repository;

import model.Video;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileVideoRepository implements VideoRepository {
    private final File file;

    public FileVideoRepository(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo.", e);
            }
        }
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar video no arquivo.", e);
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Video video = Video.fromString(line); // Converte a linha para um objeto Video
                    videos.add(video);
                } catch (IllegalArgumentException e) {
                    System.out.println("Linha Invalida ignorada: " + line); // Igonorar linhas invalidas
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler os videos do arquivo.", e);
        }
        return videos;
    }

    @Override
    public void clear() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao limpar o arquivo.", e);
        }
    }

    @Override
    public void saveAll(List<Video> videos) {
        clear(); // Limpa o arquivo antes de salvar os novos vídeos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (Video video : videos) {
                bw.write(video.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar lista de vídeos no arquivo.", e);
        }
    }

    @Override
    public void deleteByTitle(String title) {
        // Obtem todos os vídeos do repositório
        List<Video> videos = findAll();

        // Filtra os vídeos para excluir o que tem o título especificado
        List<Video> updatedVideos = videos.stream()
                .filter(video -> !video.getTitulo().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        // Verifica se o video foi realmente excluido
        if (videos.size() == updatedVideos.size()) {
            throw new IllegalArgumentException("Nenhum vídeo encontrado com o título: " + title);
        }

        // Salva a lista atualizada de volta no arquivo
        saveAll(updatedVideos);
    }
}