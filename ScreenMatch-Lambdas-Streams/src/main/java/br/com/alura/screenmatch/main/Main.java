package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scan = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=f34096cb";


    public void exibeMenu(){
        System.out.println("---------------------------------------");
        System.out.println("Digite o nome da série para busca:");
        var nomeSerie = scan.nextLine();
        System.out.println("---------------------------------------");


        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.totalSeasons(); i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+")+"&season="+i+ APIKEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalSeasons(); i++) {
//            List<DadosEpisodios> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).title());
//            }
//        }

        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.title())));

          //Exemplo stream():
//        List<String> lista = Arrays.asList("Joao", "Bia", "Felipe", "Rafa", "Julia");
//        lista.stream()
//                //Operações intermediárias:
//
//                .sorted() //ordenando
//                .limit(3) // limitando
//                .filter(n -> n.startsWith("J")) // Filtro para pegar somente o nome com a letra passada
//                //.map(n -> n.toUpperCase()) // Colocar em letras maiusculas o nome encontrado com a letra passada
//                .map(String::toUpperCase) // simplificando a linha anterior
//
//                //Operação final:
//                .forEach(System.out::println);

        List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); //Não cria de forma imutável
                //.toList() ---> cria uma lista imutável (Ex: não é possível adicionar um novo objeto)

//        System.out.println("\ntop 5 episodios:");
//        dadosEpisodios.stream()
//                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A")) //Ignora avaliações com "N/A"
//                .peek(e -> System.out.println("Primeiro Filtro(N/A): " + e))
//                .sorted(Comparator.comparing(DadosEpisodios::imdbRating).reversed())
//                .peek(e -> System.out.println("Ordenação: " + e))
//                .limit(5)
//                .peek(e -> System.out.println("Limite: " + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento: " + e))
//                .forEach(System.out::println);
//        System.out.println();

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.season() , d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("------------------------------------------------------");
        System.out.println("Digite o episódio você quer procurar");
        var trechoTitulo = scan.nextLine();
        System.out.println("------------------------------------------------------");

        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado:");
            System.out.println("Título: " + episodioBuscado.get().getTitle());
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        }else {
            System.out.println("Episódio não encontrado!");
        }
        System.out.println("------------------------------------------------------\n");
//        System.out.println("------------------------------------------------------");
//        System.out.println("A partir de que ano você deseja ver os episódios?");
//        var ano = scan.nextInt();
//        System.out.println("------------------------------------------------------");
//        scan.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getReleased() != null && e.getReleased().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                ", Episódio: " + e.getTitle() +
//                                ", Data de lançamento: " + e.getReleased().format(formatador)
//                ));
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getImdbRating() > 0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getImdbRating)));

        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getImdbRating() > 0)
                .collect(Collectors.summarizingDouble(Episodio::getImdbRating));

        System.out.println("Estatísticas " + dadosSerie.title() + ":");
        System.out.println("Média de nota dos episódios: " + est.getAverage());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Quantidade de episódios: " + est.getCount());
        System.out.println(est.getMax() +" e "+ est.getCount());


    }
}
