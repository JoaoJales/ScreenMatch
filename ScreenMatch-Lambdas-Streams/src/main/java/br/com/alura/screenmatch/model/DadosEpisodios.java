package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(@JsonAlias("Title") String title,
                             @JsonAlias("Episode") Integer episode,
                             @JsonAlias("imdbRating") String imdbRating,
                             @JsonAlias("Released") String released) {
}
