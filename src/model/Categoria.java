package model;

import java.util.Arrays;

public enum Categoria {
    DOCUMENTARIO("Documentário"),
    SERIE("Série"),
    FILME("Filme"),
    ANIMACAO("Animação");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static Categoria isValidCategoria(String categoria) {
        return Arrays.stream(Categoria.values())
                .filter(c -> c.descricao.equalsIgnoreCase(categoria) || c.name().equalsIgnoreCase(categoria))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Digite uma categoria válida!"));
    }

}
