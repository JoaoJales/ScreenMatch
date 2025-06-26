package br.com.alura.screenmatch.service;

public interface IConveteDados {
    <T> T obterDados(String json, Class<T> classe);
}
