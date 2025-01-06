package service;

import model.Video;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VideoManager {
    private final VideoRepository repository;
    private final Scanner scanner = new Scanner(System.in);
    private final VideoValidator validator;

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
        this.validator = new VideoValidator();
    }

    public void addVideo() {
        String titulo;
        String descricao;
        int duracao;
        String categoria;
        String dataPublicacaoStr;

        while (true) {
            System.out.print("Digite o título do vídeo: ");
            titulo = scanner.nextLine();
            if (validator.validateTitle(titulo, repository.findAll())) {
                break;
            }
        }

        while (true) {
            System.out.print("Digite a descrição do vídeo: ");
            descricao = scanner.nextLine();
            if (validator.validateDescription(descricao)) {
                break;
            }
        }

        while (true) {
            System.out.print("Digite a duração do vídeo (em minutos): ");
            try {
                duracao = Integer.parseInt(scanner.nextLine());
                if (validator.validateDuration(duracao)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: A duração deve ser um número válido.");
            }
        }

        categoria = validator.chooseCategory();

        while (true) {
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            dataPublicacaoStr = scanner.nextLine();
            if (validator.validateDate(dataPublicacaoStr)) {
                break;
            }
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataPublicacaoStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            repository.save(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: A data fornecida é inválida.");
        }
    }

    public List<Video> listVideos() {
        return repository.findAll();
    }

    public void updateVideo(String title) {
        List<Video> videos = repository.findAll();
        Optional<Video> optionalVideo = videos.stream()
                .filter(video -> video.getTitulo().equalsIgnoreCase(title))
                .findFirst();

        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            System.out.println("Editando vídeo: " + video);

            System.out.println("Digite o novo título do vídeo (ou deixe em branco para manter o atual): ");
            String novoTitulo = scanner.nextLine();
            if (!novoTitulo.isEmpty() && validator.validateTitle(novoTitulo, repository.findAll())) {
                video.setTitulo(novoTitulo);
            } else if (!novoTitulo.isEmpty()) {
                System.out.println("O título não foi atualizado devido a erros de validação.");
            }

            System.out.print("Nova descrição (ou deixe vazio para manter a atual): ");
            String novaDescricao = scanner.nextLine();
            if (!novaDescricao.isEmpty() && validator.validateDescription(novaDescricao)) {
                video.setDescricao(novaDescricao);
            }

            System.out.print("Nova duração (em minutos) (ou deixe vazio para manter a atual): ");
            String novaDuracaoStr = scanner.nextLine();
            if (!novaDuracaoStr.isEmpty()) {
                try {
                    int novaDuracao = Integer.parseInt(novaDuracaoStr);
                    if (validator.validateDuration(novaDuracao)) {
                        video.setDuracao(novaDuracao);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: A duração deve ser um número válido.");
                }
            }

            System.out.print("Nova categoria (ou deixe vazio para manter a atual): ");
            String novaCategoria = validator.chooseCategory();
            if (!novaCategoria.isEmpty()) {
                video.setCategoria(novaCategoria);
            }

            System.out.print("Nova data de publicação (dd/MM/yyyy) (ou deixe vazio para manter a atual): ");
            String novaDataStr = scanner.nextLine();
            if (!novaDataStr.isEmpty() && validator.validateDate(novaDataStr)) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    video.setDataPublicacao(sdf.parse(novaDataStr));
                } catch (Exception e) {
                    System.out.println("Erro: Data inválida.");
                }
            }

            repository.save(video);
            System.out.println("Vídeo atualizado com sucesso!");
        } else {
            System.out.println("Erro: Vídeo com o título fornecido não encontrado.");
        }
    }
}