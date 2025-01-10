package br.com.filmesapi.principal;

import br.com.filmesapi.model.DadosEpisodio;
import br.com.filmesapi.model.DadosMedia;
import br.com.filmesapi.model.DadosTemporada;
import br.com.filmesapi.model.Episodio;
import br.com.filmesapi.service.ConverteDados;
import br.com.filmesapi.service.OmdbApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private OmdbApi consumoapi = new OmdbApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=12002106";
    public void exibeMenu() {
        System.out.println("digite o nome do filme para busca");
        var titulo = sc.nextLine();
        var json = consumoapi.obterDados(ENDERECO + titulo.replace(" ",  "+") + API_KEY);
        DadosMedia dados = conversor.obterDados(json, DadosMedia.class);

        if("movie".equalsIgnoreCase(dados.tipo())) {
            var jsonAtualizado = dados.toString().replace("movie", "Filme");
            System.out.println(jsonAtualizado);
        } else {
            var jsonAtualizado = dados.toString().replace("series", "Serie");
            System.out.println(jsonAtualizado);

            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= dados.totalTemporadas(); i++) {
                json = consumoapi.obterDados(ENDERECO + titulo.replace(" ",  "+") +"&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.numero() + " " + e.titulo())));

            List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream())
                    .collect(Collectors.toList());

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(d -> new Episodio(t.numero(), d)))
                    .collect(Collectors.toList());

            episodios.forEach(System.out::println);
        }


    }
}
