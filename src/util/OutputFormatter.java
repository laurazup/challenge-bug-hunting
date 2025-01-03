package main;

import model.Video;

public class OutputFormatter {

    public void printVideo(Video video) {
        System.out.println("Título: " + video.getTitulo());
        System.out.println("Descrição: " + video.getDescricao());
        System.out.println("Duração: " + video.getDuracao() + " minutos");
        System.out.println("Categoria: " + video.getCategoria());
        System.out.println("Data de Publicação: " + video.getDataPublicacao());
        System.out.println("-----------------------------------");
    }
}
