package com.app.spotfind.Network;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SessoesService {
    @GET("Sessoes")
    Call<List<Sessoes>> getSessoes();

    @GET("Sessoes?filter=%7B%22where%22%3A%7B%22imdbId%22%3A%22={imdb}%22%7D%7D")
    Call<List<Sessoes>> getFilmePorImdbId(@Path(value = "imdb") String imdb);
}
