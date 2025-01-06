package main;

import model.Video;
import model.illegalArgumentException;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner src = new Scanner(System.in);
    private static final VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
    private static final SearchStrategy searchStrategy = new TitleSearchStrategy();


    // Constantes para as opções do menu
    private static final int OPCAO_ADICIONAR_VIDEO = 1;
    private static final int OPCAO_LISTAR_VIDEOS = 2;
    private static final int OPCAO_PESQUISAR_VIDEO = 3;
    private static final int OPCAO_EDITAR_VIDEO = 4;
    private static final int OPCAO_FILTRAR_POR_CATEGORIA = 5;
    private static final int OPCAO_ORDENAR_POR_DATA = 6;
    private static final int OPCAO_RELATORIO_ESTATISTICAS = 7;
    private static final int OPCAO_EXCLUIR_VIDEO = 8;
    private static final int OPCAO_SAIR = 9;

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            continuar = processarOpcao(opcao);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println(OPCAO_ADICIONAR_VIDEO + ". Adicionar vídeo");
        System.out.println(OPCAO_LISTAR_VIDEOS + ". Listar vídeos");
        System.out.println(OPCAO_PESQUISAR_VIDEO + ". Pesquisar vídeo por título");
        System.out.println(OPCAO_EDITAR_VIDEO + ". Editar vídeo");
        System.out.println(OPCAO_FILTRAR_POR_CATEGORIA + ". Filtrar vídeo por categoria");
        System.out.println(OPCAO_ORDENAR_POR_DATA + ". Ordenar vídeos por data de publicação");
        System.out.println(OPCAO_RELATORIO_ESTATISTICAS + ". Relatório de estatísticas");
        System.out.println(OPCAO_EXCLUIR_VIDEO + ". Excluir vídeo");
        System.out.println(OPCAO_SAIR + ". SAIR");
    }

    private static boolean processarOpcao(int opcao) {
        switch (opcao) {
            case OPCAO_ADICIONAR_VIDEO -> adicionarVideo();
            case OPCAO_LISTAR_VIDEOS -> listarVideos();
            case OPCAO_PESQUISAR_VIDEO -> pesquisarVideoPorTitulo();
            case OPCAO_EDITAR_VIDEO -> editarVideo();
            case OPCAO_FILTRAR_POR_CATEGORIA -> filtrarVideoPorCategoria();
            case OPCAO_ORDENAR_POR_DATA -> ordenarPorDataPublicacao();
            case OPCAO_RELATORIO_ESTATISTICAS -> exibirRelatorioEstatisticas();
            case OPCAO_EXCLUIR_VIDEO -> deleteVideo();
            case OPCAO_SAIR -> {
                System.out.println("Saindo do sistema...");
                return false; // Encerra o loop
            }
            default -> System.out.println("Opção inválida.");
        }
        return true; // Continua o loop
    }

    private static void adicionarVideo() {

        try {
            try {
                String titulo = lerTitulo("Digite o título do vídeo: ");
                String descricao = lerString("Digite a descrição do vídeo: ");
                int duracao = lerInteiro("Digite a duração do vídeo (em minutos): ");
                Date dataPublicacao = lerDataValida("Digite a data de publicação (dd/MM/yyyy): ");
                String categoria = lerString("Digite a categoria do vídeo: ");

                Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                videoService.addVideo(video);
                System.out.println("Vídeo adicionado com sucesso!");
            } catch (Exception | illegalArgumentException e) {
                System.out.println("Erro ao adicionar vídeo.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    private static void listarVideos() {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            videos.forEach(video -> {
                String dataFormatada = sdf.format(video.getDataPublicacao());
                System.out.println("Título: " + video.getTitulo() + " | " + video.getDescricao() + " | " + video.getCategoria() + " | " + video.getDuracao() + "min | " + video.getDataPublicacao());
            });
        }
    }

    private static void pesquisarVideoPorTitulo() {
        String query = lerString("Digite o título da busca: ");
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título fornecido.");
        } else {
            resultados.forEach(System.out::println);
            resultados.forEach(video -> {
                System.out.println("Título: " + video.getTitulo() + " | " + video.getDescricao() + " | " + video.getCategoria() + " | " + video.getDuracao() + "min | " + video.getDataPublicacao());

            });

        }
    }

    private static void editarVideo() {
        String query = lerString("Digite o título do vídeo que deseja editar: ");
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título fornecido.");
        } else {
            Video videoOriginal = resultados.get(0);
            System.out.println("Editando o vídeo: " + videoOriginal);
            String novoTitulo = lerString("Novo título (deixe em branco para manter o título anterior): ");
            String novaDescricao = lerString("Nova descrição (deixe em branco para manter a descrição anterior): ");
            int novaDuracao = lerInteiro("Nova duração (em minutos, 0 para manter a anterior): ");
            String novaCategoria = lerString("Nova categoria (deixe em branco para manter a anterior): ");
            String novaDataStr = lerString("Nova data de publicação (dd/MM/yyyy, deixe em branco para manter a anterior): ");
            try {
                Video videoAtualizado = videoOriginal.atualizarVideo(novoTitulo, novaDescricao, novaDuracao, novaCategoria, novaDataStr);
                videoService.updateVideo(videoOriginal, videoAtualizado);
                System.out.println("Vídeo atualizado com sucesso!");
            } catch (Exception | illegalArgumentException e) {
                System.out.println("Erro ao editar vídeo: " + e.getMessage());
            }
        }
    }

    private static void filtrarVideoPorCategoria() {
        // Solicita ao usuário a categoria que deseja filtrar
        String categoria = lerString("Digite a categoria dos vídeos que deseja filtrar: ");

        // Busca os vídeos correspondentes à categoria fornecida
        List<Video> videosFiltrados = videoService.listVideos().stream()
                .filter(video -> video.getCategoria().equalsIgnoreCase(categoria))
                .toList();

        if (videosFiltrados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado na categoria fornecida.");
        } else {
            System.out.println("Vídeos encontrados na categoria '" + categoria + "':");
            videosFiltrados.forEach(video -> {
                System.out.println("Título: " + video.getTitulo() + " | Descrição: " + video.getDescricao() +
                        " | Duração: " + video.getDuracao() + "min | Data de Publicação: " + video.getDataPublicacao());
            });
        }
    }

    private static void ordenarPorDataPublicacao() {
        // Obtém a lista de vídeos ordenados por data de publicação
        List<Video> videosOrdenados = videoService.listVideosOrderedByDate();

        if (videosOrdenados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Vídeos ordenados por data de publicação:");
            videosOrdenados.forEach(video -> {
                String dataFormatada = sdf.format(video.getDataPublicacao());
                System.out.println("Título: " + video.getTitulo() + " | " + video.getDescricao() + " | " +
                        video.getCategoria() + " | " + video.getDuracao() + "min | " + dataFormatada);
            });
        }

    }

    private static void exibirRelatorioEstatisticas() {
        List<Video> videos = videoService.listVideos();

        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado para gerar o relatório.");
            return;
        }

        // Quantidade total de vídeos
        int totalVideos = videos.size();

        // Duração média dos vídeos
        double duracaoMedia = videos.stream()
                .mapToInt(Video::getDuracao)
                .average()
                .orElse(0);

        // Vídeo mais antigo
        Video videoMaisAntigo = videos.stream()
                .min(Comparator.comparing(Video::getDataPublicacao))
                .orElse(null);

        // Vídeo mais recente
        Video videoMaisRecente = videos.stream()
                .max(Comparator.comparing(Video::getDataPublicacao))
                .orElse(null);

        // Exibir o relatório
        System.out.println("\n=== Relatório de Estatísticas ===");
        System.out.println("Quantidade total de vídeos: " + totalVideos);
        System.out.printf("Duração média dos vídeos: %.2f minutos\n", duracaoMedia);

        if (videoMaisAntigo != null) {
            System.out.println("Vídeo mais antigo: " + videoMaisAntigo.getTitulo() +
                    " (Publicado em: " + new SimpleDateFormat("dd/MM/yyyy").format(videoMaisAntigo.getDataPublicacao()) + ")");
        }

        if (videoMaisRecente != null) {
            System.out.println("Vídeo mais recente: " + videoMaisRecente.getTitulo() +
                    " (Publicado em: " + new SimpleDateFormat("dd/MM/yyyy").format(videoMaisRecente.getDataPublicacao()) + ")");
        }
    }

    private static void deleteVideo() {
        // Solicita ao usuário o título do vídeo que deseja excluir
        String titulo = lerString("Digite o título do vídeo que deseja excluir: ");

        // Busca os vídeos correspondentes ao título fornecido
        List<Video> resultados = videoService.listVideos().stream()
                .filter(video -> video.getTitulo().equalsIgnoreCase(titulo))
                .toList();

        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com o título fornecido.");
        } else {
            // Exibe os vídeos encontrados
            System.out.println("Vídeos encontrados:");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println((i + 1) + ". " + resultados.get(i));
            }

            // Solicita ao usuário qual vídeo deseja excluir
            int escolha = lerInteiro("Digite o número do vídeo que deseja excluir (ou 0 para cancelar): ");

            if (escolha > 0 && escolha <= resultados.size()) {
                Video videoParaExcluir = resultados.get(escolha - 1);

                // Remove o vídeo da lista e atualiza o repositório
                List<Video> listaAtualizada = videoService.listVideos().stream()
                        .filter(video -> !video.equals(videoParaExcluir))
                        .toList();

                videoService.updateVideoList(listaAtualizada);

                System.out.println("Vídeo excluído com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }

    private static Date lerDataValida(String mensagem) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Garante que apenas datas válidas sejam aceitas
        while (true) {
            try {
                String dataStr = lerString(mensagem);
                return sdf.parse(dataStr); // Tenta converter a string para uma data
            } catch (Exception e) {
                System.out.println("Data inválida. Por favor, insira uma data no formato dd/MM/yyyy.");
            }
        }
    }
    private static String lerString(String mensagem) {
        System.out.println(mensagem);
        return src.nextLine().trim();
    }

    private static String lerTitulo(String mensagem) throws illegalArgumentException {
        String titulo = lerString(mensagem);
        if (titulo.isEmpty()) {
            throw new illegalArgumentException("Preencha o título.");
        }
        return titulo;
    }
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(src.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
    }


}



