package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Filme;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainList {
    public static void main(String[] args) {
        ArrayList<Titulo> lista = getTitulos();

        for (Titulo item : lista){
            System.out.println(item);
            if (item instanceof Filme filme && filme.getClassificacao() > 2){
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        //ArrayList<String> buscaPorArtista = new ArrayList<>();
        List<String> buscaPorArtista = new ArrayList<>(); // Melhores práticas (Utilizar somente List<> para declarar
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Fernanda Torres");
        buscaPorArtista.add("Joao Ricardo");
        buscaPorArtista.add("Beatriz");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Depois da ordenação");
        System.out.println(buscaPorArtista);

        System.out.println("Lista de titulos ordenados:");
        Collections.sort(lista); // Após implementar o métod0 compareTo na class Titulo
        System.out.println(lista);

        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Ordenando por ano: ");
        System.out.println(lista);

    }

    private static ArrayList<Titulo> getTitulos() {
        Filme movie1 = new Filme("Toy Story", 1995);
        movie1.avalia(10);
        Filme movie2 = new Filme("Avatar", 2023);
        movie2.avalia(7);
        var movie3 = new Filme("Divertidamente", 2015);
        movie3.avalia(8);
        Serie lost = new Serie("Lost", 2000);

        Filme f1 = movie1; // Não é copiado para f1 ou criado um novo objeto, f1 passa a ser mais uma referência para movie1

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(movie1);
        lista.add(movie2);
        lista.add(movie3);
        lista.add(lost);
        return lista;
    }
}
