package com.app.spotfind.Network;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuariosService {
  @POST("Usuarios/login")
  Call<List<Usuario>> getUsuario(@Body Usuario usuario);
}
