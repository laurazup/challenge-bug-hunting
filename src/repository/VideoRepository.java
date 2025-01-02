package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;

// BUSINESS RULE:
// The ordinal number of the CategoryType enumerator,
// is directly linked to the CSV file of the VideoRepository class.
// Future changes to the enumerator must be propagated to the CSV file,
// before the program runs again.

public class VideoRepository {
    private File fileCSV = null;

    public VideoRepository(String filePathName) {
        try {
            fileCSV = new File(filePathName);
        } catch (NullPointerException e) {
            System.err.println("Não foi possível associar o arquivo " + filePathName);
        }
    }

    public void saveListOfVideos(ArrayList<Video> listOfVideos) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            // - Criar lógica com booleano append com um bloco exclusivo para addVideo
            //   from {Create, up-date and Delete}
            //     to {create}{Up-date and Delete}
            // ??? É viável aumentar a complexidade por otimização com append() ???

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

    public ArrayList<Video> loadListOfVideos() {
        ArrayList<Video> listOfVideos = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;
        Video temporaryVideo;
        String tupleCSV;

        try {
            fileReader = new FileReader(fileCSV);
            bufferedReader = new BufferedReader(fileReader);
            while ((tupleCSV = bufferedReader.readLine()) != null) {
                temporaryVideo = Video.receivesFromCSV(tupleCSV);
                if (temporaryVideo != null) {
                    listOfVideos.add(temporaryVideo);
                }
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
}