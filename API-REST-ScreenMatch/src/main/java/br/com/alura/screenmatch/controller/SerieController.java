package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.obterSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id){
        return service.findByID(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return service.obterTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{idTemporada}")
    public List<EpisodioDTO> obterTodosEpisodios(@PathVariable Long id, @PathVariable Long idTemporada){
        return service.obterTodosEpisodios(id, idTemporada);
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeCategoria){
        return service.obterSeriesPorCategoria(nomeCategoria);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterSeriesPorCategoria(@PathVariable Long id){
        return service.top5EpisodiosPorTemporada(id);
    }
}
