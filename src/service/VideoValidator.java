package service;

import model.Video;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class VideoValidator {
    private final Scanner scanner = new Scanner(System.in);

    public boolean validateTitle(String title, List<Video> existingVideos) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Erro: O título não pode ser vazio.");
            return false;
        }
        boolean exists = existingVideos.stream()
                .anyMatch(video -> video.getTitulo().equalsIgnoreCase(title));
        if (exists) {
            System.out.println("Erro: Já existe um vídeo com esse título.");
            return false;
        }
        return true;
    }

    public boolean validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Erro: A descrição não pode ser vazia.");
            return false;
        }
        return true;
    }

    public boolean validateDuration(int duration) {
        if (duration <= 0) {
            System.out.println("Erro: A duração deve ser maior que zero.");
            return false;
        }
        return true;
    }

    public boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            System.out.println("Erro: A data fornecida é inválida.");
            return false;
        }
    }

    public String chooseCategory() {
        System.out.println("Escolha a categoria do vídeo:");
        System.out.println("1 - Filme");
        System.out.println("2 - Série");
        System.out.println("3 - Documentario");
        System.out.println("4 - Outros");

        String option = scanner.nextLine();
        return switch (option) {
            case "1" -> "Filme";
            case "2" -> "Série";
            case "3" -> "Documentario";
            case "4" -> "Outros";
            default -> {
                System.out.println("Categoria inválida. Usando 'Outros' como padrão.");
                yield "Outros";
            }
        };
    }
}