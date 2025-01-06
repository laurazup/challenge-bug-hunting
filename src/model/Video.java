package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private Categoria categoria;
    private LocalDate dataPublicacao;

    public Video(String titulo, String descricao, int duracao, Categoria categoria, LocalDate dataPublicacao) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O titulo não pode ser vazio.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (duracao <= 0) {
            throw new IllegalArgumentException("Atenção!  duração tem que ser um número positico.");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria não pode ser nula.");
        }
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String novoTitulo) {
        if (novoTitulo == null || novoTitulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.titulo = novoTitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String novaDescricao) {
        if (novaDescricao == null || novaDescricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        this.descricao = novaDescricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser um número positivo.");
        }
        this.duracao = duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (!isCategoriaValida(categoria)) {
            throw new IllegalArgumentException("Categoria inválida. Use: Filme, Série ou Documentário.");
        }
        this.categoria = Categoria.valueOf(categoria.toUpperCase());
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
        }
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + dataPublicacao.format(formatter);
    }

    public static Video fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes.length != 5) {
            throw new IllegalArgumentException("Linha inválida. Esperando 5 campos separados por ponto e vírgula.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataPublicacao;
        try {
            dataPublicacao = LocalDate.parse(partes[4], formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Data invalida no formato dd/MM/yyyy" + partes[4]);
        }

        // Validação da categoria
        Categoria categoria;
        try {
            categoria = Categoria.valueOf(partes[3].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida: " + partes[3] + ". Use: FILME, SERIE ou DOCUMENTARIO.");
        }
        return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), categoria, dataPublicacao);
    }

    // Método para lançar uma exceção se for inválido, e converter a string da categoria para enum.
    private static boolean isCategoriaValida(String categoria) {
        try {
            Categoria.valueOf(categoria.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static LocalDate parseData(String dataStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }
}