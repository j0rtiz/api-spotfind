package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmaDeletaContaActivity extends AppCompatActivity {
  String usuarioHash;
  String numId;
  Usuario usuario;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirma_deleta_conta);

    usuario = (Usuario) getIntent().getExtras().get("usuario");
    usuarioHash = usuario.getId();
    numId = usuario.getUserId();
  }


  public void chamadaDeletar() {

    String uriDeletaUsuario = "Usuarios/"+numId+"?access_token="+usuarioHash;

    Call<Usuario> call = new RetrofitConfig().loginUsuarioService().deleteUsuario(uriDeletaUsuario);
    call.enqueue(new Callback<Usuario>() {
      @Override
      public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        try {
          if (response.isSuccessful()) {
            voltarLogin();
            Toast.makeText(getApplicationContext(), "Conta Deletada!", Toast.LENGTH_LONG).show();
          }
        } catch (Exception e) {
          Log.e("Delete: ", "Erro: " + e.getMessage());
          showError();
        }
      }

      @Override
      public void onFailure(Call<Usuario> call, Throwable t) {
        Log.e("Deleta conta: ", "Erro: " + t.getMessage());
      }
    });
  }

  public void voltarMain(View view) {
    Intent login = new Intent(this, MainActivity.class);

    Bundle bundle = new Bundle();

    bundle.putSerializable("usuario", usuario);
    login.putExtras(bundle);

    startActivity(login, null);
  }

  public void voltarLogin() {
    Intent login = new Intent(this, LoginActivity.class);

    startActivity(login, null);
  }

  public void showError() {
    Toast.makeText(this, R.string.erro_cadastrar, Toast.LENGTH_LONG).show();
  }

  public void deletarMesmo(View view) {
    chamadaDeletar();
  }
  }
