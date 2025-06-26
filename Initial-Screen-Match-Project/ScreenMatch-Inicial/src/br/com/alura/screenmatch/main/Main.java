package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.calculos.CalculadoraDeTempo;
import br.com.alura.screenmatch.calculos.FiltroRecomendacao;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Filme;
import br.com.alura.screenmatch.model.Serie;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Filme movie1 = new Filme("Toy Story", 1995); // Usando construtores
        movie1.setIncluidoNoPlano(true);
        movie1.setDuracaoEmMinutos(180);

        movie1.exibeFichaTecnica();
        System.out.println("Duração (min): " + movie1.getDuracaoEmMinutos());
        movie1.avalia(8);
        movie1.avalia(6);
        movie1.avalia(10);
        System.out.println("Total de avaliações: " + movie1.getTotalDeAvaliacoes());
        System.out.println("Media de Avaliações: " + movie1.mediaAvaliacoes());
        System.out.println();

        Serie lost = new Serie("Lost", 2000);
        lost.exibeFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpsisodio(50);
        System.out.println("Duração: " + lost.getDuracaoEmMinutos());

        Filme movie2 = new Filme("Avatar", 2023);
        movie2.setIncluidoNoPlano(true);
        movie2.setDuracaoEmMinutos(200);

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(movie1);
        calculadora.inclui(movie2);
        calculadora.inclui(lost);
        System.out.println(calculadora.getTempoTotal());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(movie1);

        Episodio episodio = new Episodio();
        episodio.setNome("Piloto");
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizacoes(100);
        filtro.filtra(episodio);
        System.out.println();

        var movie3 = new Filme("Divertidamente", 2015); // var: realiza uma inferência do tipo declarado // movie3 continua sendo um tipo 'Filme'
        movie3.setDuracaoEmMinutos(160);
        movie3.avalia(9);

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(movie1);
        listaDeFilmes.add(movie2);
        listaDeFilmes.add(movie3);
        System.out.println("Tamanho da lista: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.get(0).getNome());
        System.out.println("ToSring Filme: " + listaDeFilmes.get(0).toString()); //O métod0 toString foi reescrito (@Override)
        System.out.println(listaDeFilmes);
        System.out.println();

        ArrayList<Serie> listaDeSeries = new ArrayList<>();
        listaDeSeries.add(lost);
        System.out.println("Primeira serie: " + listaDeSeries.get(0).getNome());
        System.out.println("ToSring Serie: " + listaDeSeries.get(0).toString()); //O métod0 toString foi reescrito (@Override)


    }
}
