package br.com.filmesapi.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
