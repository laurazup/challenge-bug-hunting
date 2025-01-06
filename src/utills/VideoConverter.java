package utills;

import model.Video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoConverter {

    public static Video fromString(String linha) {
        if (linha == null || linha.isEmpty()) {
            throw new IllegalArgumentException("A linha não pode ser nula ou vazia.");
        }

        String[] partes = linha.split(";");
        try {
            String titulo = partes[0];
            String descricao = partes[1];
            int duracao = Integer.parseInt(partes[2]);
            Categoria categoria = Categoria.valueOf(partes[3]);
            Date dataPublicacao = new SimpleDateFormat("dd/MM/yyyy").parse(partes[4]);

            return new Video(titulo, descricao, duracao, categoria, dataPublicacao);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("erro ao converter a duração para número" + e.getMessage(), e);
        } catch (ParseException e) {
            throw new IllegalArgumentException("erro para converter a data" + e.getMessage(), e);
        }
    }

    public static String toString(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("O vídeo não pode ser nulo.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.join(";",
                video.getTitulo(),
                video.getDescricao(),
                String.valueOf(video.getDuracao()),
                video.getCategoria().name(),
                sdf.format(video.getDataPublicacao())
        );

    }
}