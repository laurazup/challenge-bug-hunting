package main;

import model.Video;
import repository.FileVideoRepositoryImpl;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import util.Menu;
import service.VideoManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        FileVideoRepositoryImpl fileVideoRepository = new FileVideoRepositoryImpl("videos.txt");
        SearchStrategy searchStrategy = new SearchStrategyImpl();
        VideoServiceImpl videoService = new VideoServiceImpl(fileVideoRepository, searchStrategy);
        VideoManager videoManager = new VideoManager(videoService);

        Menu menu = new Menu(videoService, searchStrategy, scanner);

        while (true) {
            int opcao = menu.exibirMenu();

                switch (opcao) {
                    case 1:
                        adicionarVideo(menu);
                        break;

                    case 2:
                        listarVideos(videoManager);
                        break;

                    case 3:
                       pesquisarTitulo(videoService, searchStrategy, scanner);
                        break;

                    case 4:
                        editarVideo();
                        break;

                    case 5:
                        excluirVideo();
                        break;

                    case 6:
                        filtrarVideo();
                        break;

                    case 7:
                        ordenarVideo();
                        break;

                    case 8:
                        mostrarRelatorio();
                        break;

                    case 9:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }

        }

    private static void adicionarVideo(Menu menu) {
        menu.adicionarVideo();
    }

    private static void listarVideos(VideoManager videoService) {
        List<Video> videos = videoService.listarVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    private static void pesquisarTitulo(VideoServiceImpl videoService, SearchStrategy searchStrategy, Scanner scanner) {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo com esse título foi encontrado.");
        }
        for (Video video : resultados) {
            System.out.println(video);
        }
    }
    public static void editarVideo(){

    }

    public static void excluirVideo(){

    }

    public static void filtrarVideo(){

    }

    public static void ordenarVideo(){

    }

    public static void mostrarRelatorio(){

    }
}