package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private Categoria categoria;
    private String dataPublicacao;

    public Video(String titulo, String descricao, int duracao, Categoria categoria, String dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + dataPublicacao;
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), Categoria.isValidCategoria(partes[3]), partes[4]);
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}