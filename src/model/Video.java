package model;

import utills.Categoria;

import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private Categoria categoria;
    private Date dataPublicacao;

    public Video(String titulo, String descricao, int duracao, Categoria categoria, Date dataPublicacao) {
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

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

}