package com.app.spotfind.Network;

import com.app.spotfind.Models.Sessoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface SessoesService {
    @GET("Sessoes")
    Call<List<Sessoes>> getSessoes();

    @GET
    Call<List<Sessoes>> getFilmePorImdbId(@Url String imdb);
}
