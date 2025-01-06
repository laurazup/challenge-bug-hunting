package model;

import Validations.Validations;
import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoService;
import service.VideoServiceImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileHandler extends VideoServiceImpl {
    private final VideoService videoService;

    public FileHandler(VideoRepository repository) {
        super(repository);
        this.videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
    }

    public void saveVideo() {
        try {
            Scanner scanner = new Scanner(System.in);
            String titulo = Validations.readNonEmptyString(scanner, "Digite o título do vídeo: ", "O título não pode ser nulo!");
            String descricao = Validations.readNonEmptyString(scanner, "Digite a descrição do vídeo: ", "A descrição não pode ser nula!");
            int duracao = Validations.readPositiveInt(scanner, "Digite a duração do vídeo (em minutos): ");
            String categoria = Validations.selectCategory(scanner);
            Date dataPublicacao = Validations.readValidDate(scanner, "Digite a data de publicação (dd/MM/yyyy): ");

            // Criação do vídeo e adição ao serviço
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi possível adicionar o vídeo. Erro: " + e.getMessage());
        }
    }

    public void listingVideos() {
        List<Video> videos = videoService.listVideos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        for (Video video : videos) {
            index++;
            System.out.println(index + " - Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }
    }


    public void editVideo() {
        Scanner scanner = new Scanner(System.in);

        List<Video> videos = videoService.listVideos();

        listingVideos();

        System.out.println("Escolha o número do vídeo que deseja editar: ");
        int listIndex = scanner.nextInt() - 1;

        if (listIndex < 0 || listIndex >= videos.size()) {
            System.out.println("Índice inválido! Nenhum vídeo foi alterado.");
            return;
        }

        Video newList = videos.get(listIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("1 - Título: " + newList.getTitulo() +
                "\n2 - Descrição: " + newList.getDescricao() +
                "\n3 - Duração: " + newList.getDuracao() +
                "\n4 - Categoria: " + newList.getCategoria() +
                "\n5 - Data de publicação: " + sdf.format(newList.getDataPublicacao()));
        System.out.println("Escolha o número do que você quer editar no vídeo: ");
        int itemIndex = scanner.nextInt() - 1;

        try {
            switch (itemIndex) {
                case 0 -> {
                    String titulo = Validations.readNonEmptyString(scanner, "Digite o novo título do vídeo: ", "O título não pode ser nulo!");
                    newList.setTitulo(titulo);
                }
                case 1 -> {
                    String descricao = Validations.readNonEmptyString(scanner, "Digite a nova descrição do vídeo: ", "A descrição não pode ser nulo!");
                    newList.setDescricao(descricao);
                }
                case 2 -> {
                    int duracao = Validations.readPositiveInt(scanner, "Digite a duração do vídeo (em minutos): ");
                    newList.setDuracao(duracao);
                }
                case 3 -> {
                    String categoria = Validations.selectCategory(scanner);
                    newList.setCategoria(categoria);
                }
                case 4 -> {
                    Date dataPublicacao = Validations.readValidDate(scanner, "Digite a data de publicação (dd/MM/yyyy): ");
                    newList.setDataPublicacao(dataPublicacao);
                }
                default -> {
                    System.out.println("Opção inválida! Nenhum campo foi alterado.");
                    return;
                }
            }
            System.out.println("Item alterado com sucesso!");

            // Salvar a lista atualizada no arquivo
            saveVideosToFile(videos);

        } catch (Exception e) {
            System.out.println("Erro ao editar o arquivo: " + e.getMessage());
        }
    }

    private void saveVideosToFile(List<Video> videos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("videos.txt"))) {
            for (Video video : videos) {
                // Serializar o objeto Video em uma string
                String videoData = String.join(";",
                        video.getTitulo(),
                        video.getDescricao(),
                        String.valueOf(video.getDuracao()),
                        video.getCategoria(),
                        new SimpleDateFormat("dd/MM/yyyy").format(video.getDataPublicacao())
                );
                writer.write(videoData);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar os vídeos no arquivo: " + e.getMessage());
        }
    }


    public void deleteVideo() {
        Scanner scanner = new Scanner(System.in);

        List<Video> videos = videoService.listVideos();

        listingVideos();

        System.out.println("Escolha o número do vídeo que deseja excluir: ");
        int listIndex = scanner.nextInt() - 1;

        if (listIndex < 0 || listIndex >= videos.size()) {
            System.out.println("Índice inválido! Nenhum vídeo foi deletado.");
            return;
        }
        try {
            videos.remove(listIndex);

            // Reescrever o arquivo com as linhas restantes
            saveVideosToFile(videos);

            System.out.println("Vídeo: " + (listIndex + 1) + " removido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

    }
}
