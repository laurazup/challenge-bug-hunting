package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private Categoria categoria; // Enum categoria
    private Date dataPublicacao;

    // Construtor com validação
    public Video(String titulo, String descricao, int duracao, Categoria categoria, Date dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    // Getters
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

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    // Setters com validação
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
            this.categoria = Categoria.valueOf(categoria.toUpperCase()); // Convertendo para maiúsculas
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: FILME, SERIE, DOCUMENTARIO, OUTROS.");
        }
    }

    public void setDataPublicacao(Date dataPublicacao) {
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
        }
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }

    // Metodo para criar um Video a partir de uma string formatada
    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Categoria categoria = Categoria.valueOf(partes[3].toUpperCase());
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), categoria, sdf.parse(partes[4]));
        } catch (Exception e) {
            return null; // Retorna null em caso de erro
        }
    }
}
