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
        } catch (NullPointerException e) {
            System.err.println("Não foi possível associar o arquivo " + filePathName);
        }
    }

    public void save(ArrayList<Video> listOfVideos) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(fileCSV);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Video video : listOfVideos) {
                bufferedWriter.write(video.sendsToCSV());
                try {
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    System.err.println("Não foi possível adiciona uma nova linha no arquivo");
                }
            }

            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("Não foi possível fechar os arquivos");
            }
        } catch (IOException e) {
            System.err.println("Não foi possível abrir o arquivo" + fileCSV.getName() + "para escrita");
        }
    }

    public ArrayList<Video> load() {
        ArrayList<Video> listOfVideos = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;
        String tupleCSV;

        try {
            fileReader = new FileReader(fileCSV);
            bufferedReader = new BufferedReader(fileReader);
            while ((tupleCSV = bufferedReader.readLine()) != null) {
                listOfVideos.add(Video.receivesFromCSV(tupleCSV));
            }

            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                System.err.println("Não foi possível fechar o arquivo");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Não foi possível abrir o arquivo" + fileCSV.getName() + "para leitura");
        } catch (IOException e) {
            System.err.println("Não foi possível ler a linha do arquivo" + fileCSV.getName());
        }

        return listOfVideos;
    }

    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.receivesFromCSV(line);
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