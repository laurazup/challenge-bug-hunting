package controller;

import model.PrincipalMenu;
import model.Video;
import service.VideoService;
import util.ScannerUtil;
import java.util.List;
import java.util.Scanner;

public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    public void editVideo(Scanner scanner) {
        List<Video> videoList = videoService.listVideos();
        if (videoList.isEmpty()) {
            System.out.println("Não há vídeos cadastrados.");
            return;
        }

        // Exibe os vídeos disponíveis
        showVideos(videoList);

        // Obtém o índice válido do vídeo a ser editado
        int index = getValidVideoIndex(scanner, videoList.size());

        // Edita os atributos do vídeo selecionado
        Video updatedVideo = editVideoByAttributes(scanner, videoList.get(index));

        // Atualiza o vídeo no serviço
        videoService.updateVideo(index, updatedVideo);

        System.out.println("Vídeo editado com sucesso!");
    }

    public void deleteVideo(Scanner scanner) {
        List<Video> videoList = videoService.listVideos();
        if (videoList.isEmpty()) {
            System.out.println("Não há vídeos cadastrados.");
            return;
        }

        // Exibe os vídeos disponíveis
        showVideos(videoList);

        // Obtém o índice válido do vídeo a ser deletado
        int index = getValidVideoIndex(scanner, videoList.size());

        // Remove o vídeo no serviço
        videoService.deleteVideo(index);

        System.out.println("Vídeo deletado com sucesso!");
    }

    private void showVideos(List<Video> videos) {
        for (int i = 0; i < videos.size(); i++) {
            System.out.println((i + 1) + ". " + videos.get(i));
        }
    }

    private int getValidVideoIndex(Scanner scanner, int size) {
        int index;
        while (true) {
            System.out.print("Digite o número do vídeo: ");
            index = ScannerUtil.readInt(scanner, "Digite o número do vídeo: ") - 1;
            if (index >= 0 && index < size) {
                break;
            }
            System.out.println("Índice inválido. Por favor, insira um número entre 1 e " + size + ".");
        }
        return index;
    }

    private Video editVideoByAttributes(Scanner scanner, Video video) {
        System.out.println("Editando o vídeo: " + video);
        System.out.println("1. Título: " + video.getTitulo());
        System.out.println("2. Descrição: " + video.getDescricao());
        System.out.println("3. Duração (minutos): " + video.getDuracao());
        System.out.println("4. Categoria: " + video.getCategoria());
        System.out.println("5. Data de publicação: " + video.getDataPublicacao());

        int option;
        do {
            option = ScannerUtil.readInt(scanner, "Digite o número do atributo a ser alterado (ou 0 para finalizar edição): ");
            switch (option) {
                case 0 -> System.out.println("Fim da edição.");
                case 1 -> video.setTitulo(ScannerUtil.readString(scanner, "Digite o novo título: "));
                case 2 -> video.setDescricao(ScannerUtil.readString(scanner, "Digite a nova descrição: "));
                case 3 -> video.setDuracao(ScannerUtil.readInt(scanner, "Digite a nova duração (em minutos): "));
                case 4 -> video.setCategoria(ScannerUtil.readString(scanner, "Digite a nova categoria: "));
                case 5 -> video.setDataPublicacao(ScannerUtil.readDateFromScanner(scanner, "Digite a nova data de publicação (dd/MM/yyyy): "));
                default -> System.out.println("Opção inválida. Por favor, insira um número entre 0 e 5.");
            }
        } while (option != 0);

        return video;
    }
}