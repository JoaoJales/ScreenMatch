package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String title;
    private Integer episode;
    private Double imdbRating;
    private LocalDate released;

    public Episodio(Integer season, DadosEpisodios dadosEpisodios) {
        this.temporada = season;
        this.title = dadosEpisodios.title();
        this.episode = dadosEpisodios.episode();
        try {
            this.imdbRating = Double.valueOf(dadosEpisodios.imdbRating());
        }catch (NumberFormatException e){
            this.imdbRating = 0.0;
        }

        try {
            this.released = LocalDate.parse(dadosEpisodios.released());
        }catch (DateTimeParseException e){
            this.released = null;
        }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", imdbRating=" + imdbRating +
                ", released=" + released;
    }
}
