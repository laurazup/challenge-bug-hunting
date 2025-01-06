package model;

import Validations.Validations;
import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoServiceImpl;
import strategy.CategorySearchStrategy;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.SimpleDateFormat;
import java.util.*;

public class VideoManager extends VideoServiceImpl {
    public VideoManager(VideoRepository repository) {
        super(repository);
    }

    public void searchByTitle() {
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        VideoManager videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o título do vídeo para pesquisa: ");
        String query = scanner.nextLine();

        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);

        int index = 0;
        for (Video video : resultados) {
            index++;
            System.out.println(index + " - Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }
    }

    public void searchByCategory() {
        SearchStrategy searchStrategy = new CategorySearchStrategy();
        VideoManager videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Scanner scanner = new Scanner(System.in);

        String query = Validations.selectCategory(scanner);

        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);

        int index = 0;
        for (Video video : resultados) {
            index++;
            System.out.println(index + " - Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }
    }

    public void SortListByDate() {
        VideoManager videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        List<Video> videos = videoService.listVideos();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        videos.sort((v1, v2) -> {
            try {
                Date date1 = v1.getDataPublicacao(); // Campo de data na posição 1
                Date date2 = v2.getDataPublicacao();
                return date1.compareTo(date2);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao analisar a data", e);
            }
        });

        System.out.println("\nLista em ordem cronológica: ");
        int index = 0;
        for (Video video : videos) {
            index++;
            System.out.println(index + " - Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }

    }
}
