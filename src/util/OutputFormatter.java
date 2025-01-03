package util;

import model.Video;

public class OutputFormatter {

    public void printVideo(Video video) {
        System.out.println("""
                
                """);
        System.out.println("Título: " + video.getTitle());
        System.out.println("Descrição: " + video.getDescription());
        System.out.println("Duração: " + video.getDuration() + " minutos");
        System.out.println("Categoria: " + video.getCategory());
        System.out.println("Data de Publicação: " + video.getDate());
        System.out.println("-----------------------------------");

    }
}
