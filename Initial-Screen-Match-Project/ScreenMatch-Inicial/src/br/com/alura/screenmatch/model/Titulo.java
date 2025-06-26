package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.exception.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
    // Classe: Especifica o conteúdo do objeto // Abstração // ("Quais caracteristicas Tod0 filme tem em comum")
    //@SerializedName("Title") // Alternativa para o record, porém pior
    private String nome;
    private int anoDeLancamento;
    private int duracaoEmMinutos;
    private boolean incluidoNoPlano;
    private double somaAvaliacoes; // Private: deixa a visiblidade do atributo privada
    private int totalDeAvaliacoes;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOMDB meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();
        if (meuTituloOmdb.year().length() > 4){
            throw new ErroDeConversaoDeAnoException("Erro na conversão do ano de lançamento");
        }
        this.anoDeLancamento = Integer.valueOf(meuTituloOmdb.year());
        this.duracaoEmMinutos = Integer.valueOf(meuTituloOmdb.runtime().substring(0,2));
    }

    //criando getters:
    public String getNome() {
        return nome;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getTotalDeAvaliacoes(){
        return totalDeAvaliacoes;
    }
    //criando setters:
    public void setNome(String nome) {
        this.nome = nome;  // this: especifica o atributo nome da class filme
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    //metodo void:
    public void exibeFichaTecnica() {
        System.out.println("Nome: " + nome);
        System.out.println("Ano de Lançamento: " + anoDeLancamento);
    }

    public void avalia (double nota) {
        somaAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double mediaAvaliacoes () {
        return somaAvaliacoes / totalDeAvaliacoes;
    }


    // Utilizando a interface Comparable e o metod0 compareTo para criar o critério de ordenação
    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    @Override
    public String toString() {
        return  "(Nome = " + nome +
                ", Ano de Lançamento = " + anoDeLancamento +
                ", Duração em minutos = "+duracaoEmMinutos+")";
    }
}
