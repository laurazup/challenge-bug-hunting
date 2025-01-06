
import controller.MenuController;
import repository.FileVideoRepository;
import service.VideoManager;

public class Main {
    public static void main(String[] args) {
    // Incializa o repositorio de videos baseado em arquivo.
        FileVideoRepository repository = new FileVideoRepository("videos.txt");

        // Inicializa o gerenciador de videos que centraliza a lógica de negocio.
        VideoManager videoManager = new VideoManager(repository);

        // Inicializa o controlador do menu que gerencia a interação com o usuario.
        MenuController menuController = new MenuController(videoManager);

        // Inicia o sistema
        menuController.start();
    }
}