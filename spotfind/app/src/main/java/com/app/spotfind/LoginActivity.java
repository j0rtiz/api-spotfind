package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;
import com.app.spotfind.Network.SessoesService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

  }

  public void Login(View view) {

    TextView userEmail = findViewById(R.id.textEmail);
    TextView password = findViewById(R.id.textSenha);

    Usuario usuario = new Usuario();
    usuario.setEmail(userEmail.getText().toString());
    usuario.setPassword(password.getText().toString());


    Call<List<Usuario>> call = new RetrofitConfig().getUsuariosService().getUsuario(usuario);
    call.enqueue(new Callback<List<Usuario>>() {
      @Override
      public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
        System.out.println(response.body());
      }

      @Override
      public void onFailure(Call<List<Usuario>> call, Throwable t) {
        Log.e("Sessoes: ", "Erro: " + t.getMessage());
      }
    });
  }


}
