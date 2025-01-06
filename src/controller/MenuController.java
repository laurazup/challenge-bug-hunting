package controller;

import model.Categoria;
import model.Video;
import model.VideoDTO;
import service.VideoManager;
import strategy.CategorySearchStrategy;
import strategy.TitleSearchStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final VideoManager videoManager;
    private final Scanner scanner;

    public MenuController(VideoManager videoManager) {
        this.videoManager = videoManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            exibirMenu();
            int opcao = capturarOpcao();
            switch (opcao) {
                case 1 -> adicionarVideo();
                case 2 -> listarVideos();
                case 3 -> pesquisarPorTitulo();
                case 4 -> editarVideo();
                case 5 -> excluirVideo();
                case 6 -> filtrarPorCategoria();
                case 7 -> ordenarPorDataPublicacao();
                case 8 -> exibirRelatorioEstatisticas();
                case 9 -> sair();
                default -> System.out.println("Opção inválida. Por favor, escolha uma das opções do menu.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Editar vídeo");
        System.out.println("5. Excluir vídeo");
        System.out.println("6. Filtrar vídeos por categoria");
        System.out.println("7. Ordenar vídeos por data de publicação");
        System.out.println("8. Exibir relatório de estatísticas");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int capturarOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Retorna uma opção invalida caso a entrada não seja um numero.
        }
    }

    private void adicionarVideo() {
        try {
            System.out.println("Digite o titulo do video: ");
            String titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("O título não pode ser vazio.");
                return;
            }

            System.out.print("Digite a descrição do vídeo: ");
            String descricao = scanner.nextLine().trim();
            if (descricao.isEmpty()) {
                System.out.println("A descrição não pode ser vazia.");
                return;
            }

            System.out.print("Digite a duração do vídeo (em minutos): ");
            int duracao;
            try {
                duracao = Integer.parseInt(scanner.nextLine().trim());
                if (duracao <= 0) {
                    System.out.println("A duração deve ser um número positivo.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. A duração deve ser um número.");
                return;
            }

            System.out.print("Digite a categoria do vídeo (Filme, Série, Documentário): ");
            String categoria = scanner.nextLine().trim();
            try {
                Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
                // Validação feita com o enum Categoria
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Use: Filme, Série ou Documentário.");
                return;
            }

            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            String dataPublicacaoStr = scanner.nextLine().trim();
            LocalDate dataPublicacao;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataPublicacao = LocalDate.parse(dataPublicacaoStr, formatter);
            } catch (Exception e) {
                System.out.println("Data de publicação inválida. Use o formato dd/MM/yyyy.");
                return;
            }

            // Criação do DTO para validação e conversão
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setTitulo(titulo);
            videoDTO.setDescricao(descricao);
            videoDTO.setDuracao(duracao);
            videoDTO.setCategoria(categoria);
            videoDTO.setDataPublicacao(dataPublicacaoStr);

            // Conversão do DTO para a entidade Video
            Video video = videoDTO.toVideo();

            // Adicionar o vídeo ao sistema
            videoManager.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");

        } catch (Exception e) {
            // Tratamento de erros gerais
            System.out.println("Erro ao adicionar vídeo: " + e.getMessage());
        }
    }

    private void listarVideos() {
        List<Video> videos = videoManager.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum video encontrado.");
        } else {
            System.out.println("\n=== Lista de Videos ===");
            videos.forEach(System.out::println);
        }
    }


    private void pesquisarPorTitulo() {
        System.out.print("Digite o título para busca: ");
        String tituloBusca = scanner.nextLine().trim();
        List<Video> videosPorTitulo = videoManager.searchVideos(new TitleSearchStrategy(), tituloBusca);
        if (videosPorTitulo.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título: " + tituloBusca);
        } else {
            System.out.println("\n=== Resultados da Busca por Título ===");
            videosPorTitulo.forEach(System.out::println);
        }
    }

    private void editarVideo() {
        System.out.print("Digite o título do vídeo que deseja editar: ");
        String titulo = scanner.nextLine().trim();
        List<Video> videos = videoManager.searchVideos(new TitleSearchStrategy(), titulo);
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título: " + titulo);
            return;
        }

        Video video = videos.get(0); // Considerando que o título é único
        System.out.println("Editando vídeo: " + video);

        System.out.print("Digite o novo título (ou pressione Enter para manter): ");
        String novoTitulo = scanner.nextLine().trim();
        if (!novoTitulo.isEmpty()) video.setTitulo(novoTitulo);

        System.out.print("Digite a nova descrição (ou pressione Enter para manter): ");
        String novaDescricao = scanner.nextLine().trim();
        if (!novaDescricao.isEmpty()) video.setDescricao(novaDescricao);

        System.out.print("Digite a nova duração (ou pressione Enter para manter): ");
        String novaDuracao = scanner.nextLine().trim();
        if (!novaDuracao.isEmpty()) video.setDuracao(Integer.parseInt(novaDuracao));

        System.out.print("Digite a nova categoria (ou pressione Enter para manter): ");
        String novaCategoria = scanner.nextLine().trim();
        if (!novaCategoria.isEmpty()) video.setCategoria(novaCategoria);

        System.out.print("Digite a nova data de publicação (ou pressione Enter para manter): ");
        String novaData = scanner.nextLine().trim();
        if (!novaData.isEmpty()) video.setDataPublicacao(Video.parseData(novaData));

        videoManager.updateVideo(titulo, video);
        System.out.println("Vídeo editado com sucesso!");
    }


    private void excluirVideo() {
        System.out.print("Digite o título do vídeo que deseja excluir: ");
        String titulo = scanner.nextLine().trim();
        try {
            videoManager.deleteVideo(titulo);
            System.out.println("Vídeo excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private void filtrarPorCategoria() {
        System.out.print("Digite a categoria para filtrar (Filme, Série, Documentário): ");
        String categoria = scanner.nextLine().trim();
        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
            List<Video> videos = videoManager.searchVideos(new CategorySearchStrategy(), categoriaEnum.name());
            if (videos.isEmpty()) {
                System.out.println("Nenhum vídeo encontrado na categoria: " + categoria);
            } else {
                System.out.println("\n=== Vídeos na Categoria: " + categoria + " ===");
                videos.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria inválida. Use: Filme, Série ou Documentário.");
        }
    }

    private void ordenarPorDataPublicacao() {
        List<Video> videos = videoManager.listVideos();
        videos.sort((v1, v2) -> v1.getDataPublicacao().compareTo(v2.getDataPublicacao()));
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            System.out.println("\n=== Vídeos Ordenados por Data de Publicação ===");
            videos.forEach(System.out::println);
        }
    }

    private void exibirRelatorioEstatisticas() {
        List<Video> videos = videoManager.listVideos();
        long totalVideos = videos.size();
        long totalDuracao = videos.stream().mapToInt(Video::getDuracao).sum();
        long totalCategorias = videos.stream().map(Video::getCategoria).distinct().count();

        System.out.println("\n=== Relatório de Estatísticas ===");
        System.out.println("Total de vídeos: " + totalVideos);
        System.out.println("Duração total (em minutos): " + totalDuracao);
        System.out.println("Total de categorias: " + totalCategorias);

    }

    private void sair() {
        System.out.println("Você optou por sair, obrigado por ultilisar o sistema, volte semppre!");
        scanner.close();
        System.exit(0);

    }
}
