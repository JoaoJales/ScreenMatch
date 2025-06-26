package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterSeries() {
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());

    }

    public List<SerieDTO> obterLancamentos(){
//        return converteDados(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
        return converteDados(repository.lancamentos());
    }

    private List<SerieDTO> converteDados(List<Serie> series){
        if (series.isEmpty()) return null;
        return  series.stream()
                .map(t -> new SerieDTO(t.getId(), t.getTitulo(),
                        t.getTotalTemporadas(), t.getAvaliacao(),
                        t.getSinopse(), t.getAtores(), t.getGenero(), t.getPoster()))
                .collect(Collectors.toList());
    }


    public SerieDTO findByID(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie t = serie.get();
            return new SerieDTO(t.getId(), t.getTitulo(),
                    t.getTotalTemporadas(), t.getAvaliacao(),
                    t.getSinopse(), t.getAtores(), t.getGenero(), t.getPoster());
        }

        return null;
    }

    public List<EpisodioDTO> obterTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodosEpisodios(Long id, Long idTemporada) {
        List<Episodio> episodios = repository.obterEpisodios(id, idTemporada);
        if (episodios.isEmpty()) return null;

        return episodios.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeCategoria) {
        var categoria = Categoria.fromStringPtBr(nomeCategoria);
        return converteDados(repository.obterSeriesPorCategoria(categoria));
    }

    public List<EpisodioDTO> top5EpisodiosPorTemporada(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            List<Episodio> episodios = repository.topEpisodiosPorSerie(serie.get());

            return episodios.stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());

        }
        return null;
    }
}
