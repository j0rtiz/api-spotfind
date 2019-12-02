package com.app.spotfind.Network;

import com.app.spotfind.Models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuariosService {
    @POST("Usuarios/login")
    Call<Usuario> loginUsuario(@Body Usuario usuario);
}
