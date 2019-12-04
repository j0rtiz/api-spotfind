package com.app.spotfind.Network;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ComprasService {
  @GET
  Call<List<Compras>> getComprasPorUsuario(@Url String url);

  @POST("Compras")
  Call<Compras> postCompra(@Body Compras compra);

}
