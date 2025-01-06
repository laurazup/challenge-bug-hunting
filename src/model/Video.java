package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Video {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private Categoria categoria;
    private LocalDate dataPublicacao;

    public Video(String titulo, String descricao, int duracao, Categoria categoria, String dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = LocalDate.parse(dataPublicacao, DATE_TIME_FORMATTER);
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }
        this.descricao = descricao;
    }
    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser um número positivo.");
        }
        this.duracao = duracao;
    }
    public void setCategoria(String categoria) {
        try {
            this.categoria = Categoria.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: FILME ou SERIE");
        }
    }
    public void setDataPublicacao(String dataPublicacao) {
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
        }
        this.dataPublicacao = LocalDate.parse(dataPublicacao);
    }
    @Override
    public String toString() {
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + dataPublicacao.format(DATE_TIME_FORMATTER);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            // Usando o DATE_TIME_FORMATTER para parse da data
            Categoria categoria = Categoria.valueOf(partes[3].toUpperCase());
            LocalDate dataPublicacao = LocalDate.parse(partes[4], DATE_TIME_FORMATTER);
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), categoria, dataPublicacao.format(DATE_TIME_FORMATTER));
        } catch (Exception e) {
            return null;
        }
    }

}