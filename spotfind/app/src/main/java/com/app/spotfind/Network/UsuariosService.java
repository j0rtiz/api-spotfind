package com.app.spotfind.Network;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface UsuariosService {
    @POST("Usuarios/login")
    Call<Usuario> loginUsuario(@Body Usuario usuario);

    @POST("Usuarios")
    Call<Usuario> postCadastroUsuario(@Body Usuario novoUsuario);

    @DELETE
    Call<Usuario> deleteUsuario(@Url String UrlDeletaUsuario);
}
