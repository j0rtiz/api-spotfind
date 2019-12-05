package com.app.spotfind.Network;

import com.app.spotfind.Models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuariosService {
    @POST("Usuarios/login")
    Call<Usuario> loginUsuario(@Body Usuario usuario);

    @POST("Usuarios")
    Call<Usuario> postCadastroUsuario(@Body Usuario novoUsuario);

    @DELETE("Usuarios/{id}")
    Call<Usuario> deleteUsuario(@Path("id") String id);
}
