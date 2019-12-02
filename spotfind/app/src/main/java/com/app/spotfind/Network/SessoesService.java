package com.app.spotfind.Network;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Filme;
import com.app.spotfind.Models.Sessoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SessoesService {
    @GET("Sessoes")
    Call<List<Sessoes>> getSessoes();

    @GET("Compras?filter=%7B\"where\"%3A%7B\"usuarioId\"%3A{usuarioId}%7D%7D")
    Call<List<Compras>> getComprasPorUsuarioId(int usuarioId);

    @GET("Sessoes?filter=%7B%22where%22%3A%7B%22imdbId%22%3A%22{imdbId}%22%7D%7D")
    Call<List<Filme>> getFilmePorImdbId(String imbdId);
}
