package com.app.spotfind.Network;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComprasService {

  @GET("Compras")
  Call<List<Compras>> getComprasPorUsuarioId(@Query("filter") String usuario);

  //Compras + ?filter + =%7B%22where%22%3A%7B%22usuarioId%22%3A1%7D%7D
  //https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android-com-retrofit/
  //https://www.vogella.com/tutorials/Retrofit/article.html

}
