package br.com.alura.screenmatch.calculos;


public class FiltroRecomendacao {

    public void filtra(Classificavel classificavel){
        if (classificavel.getClassificacao() >= 4){
            System.out.println("Está estre os melhores do momento!");
        }else if (classificavel.getClassificacao() >= 2 ){
            System.out.println("Bem avaliado no momento!");
        }else {
            System.out.println("Coloque na sua lista para assistir depois!");
        }

    }
}
