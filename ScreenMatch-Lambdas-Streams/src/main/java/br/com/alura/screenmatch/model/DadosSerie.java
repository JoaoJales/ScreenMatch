package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String title,
                         @JsonAlias("totalSeasons") Integer totalSeasons,
                         @JsonAlias("imdbRating") String imdbRating) {
    @Override
    public String toString() {
        return "Título: " + this.title + ", Total de temporadas: " + this.totalSeasons + ", Avaliação: " + this.imdbRating;
    }
}
