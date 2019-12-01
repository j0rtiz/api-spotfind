package com.app.spotfind.Network;

import com.app.spotfind.Models.Sessoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SessoesService {
    @GET("Sessoes")
    Call<List<Sessoes>> getSessoes();
}
