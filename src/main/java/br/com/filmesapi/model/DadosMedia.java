package br.com.filmesapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMedia(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Year") String ano,
        @JsonAlias("Released") String dataLancamento,
        @JsonAlias("Runtime") String duracao,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Actors") String atores,
        @JsonAlias("Plot") String sinopse,
        @JsonAlias("Poster") String poster,
        @JsonAlias("imdbRating") String nota,
        @JsonAlias("Type") String tipo,
        @JsonAlias("totalSeasons")
        Integer totalTemporadas) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: ").append(titulo).append('\n')
                .append("Ano: ").append(ano).append('\n')
                .append("Data de Lançamento: ").append(dataLancamento).append('\n')
                .append("Duração: ").append(duracao).append('\n')
                .append("Gênero: ").append(genero).append('\n')
                .append("Atores: ").append(atores).append('\n')
                .append("Sinopse: ").append(sinopse).append('\n')
                .append("Poster: ").append(poster).append('\n')
                .append("Nota: ").append(nota).append('\n')
                .append("Tipo: ").append(tipo).append('\n');

        // Adiciona o campo totalTemporadas apenas se o tipo for série
        if ("series".equalsIgnoreCase(tipo)) {
            sb.append("Temporadas: ").append(totalTemporadas).append('\n');
        }

        return sb.toString();
    }
}
