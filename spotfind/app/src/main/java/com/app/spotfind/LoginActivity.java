package com.app.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
  Usuario emailCadastradoNovo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

  }

  public void Login(View view) {
    TextView userEmail = findViewById(R.id.textEmail);
    TextView password = findViewById(R.id.textSenha);

//    if(emailCadastradoNovo != null){
//      userEmail.setText(emailCadastradoNovo.toString());
////      userEmail.setFocusable(false);
//    }

    final Usuario usuario = new Usuario();
    usuario.setEmail(userEmail.getText().toString());
    usuario.setPassword(password.getText().toString());

    Call<Usuario> call = new RetrofitConfig().loginUsuarioService().loginUsuario(usuario);
    call.enqueue(new Callback<Usuario>() {
      @Override
      public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        usuario.setPassword(null);
        Usuario login = response.body();

        try {
          if (login.getId() != null) {
            usuario.setId(login.getId());
            usuario.setUserId(login.getUserId());
            goToMainActivity(usuario);
          }
        } catch (Exception e) {
          Log.e("Usuarios: ", "Erro: " + e.getMessage());
          showError();
        }
      }

      @Override
      public void onFailure(Call<Usuario> call, Throwable t) {
        Log.e("Usuarios: ", "Erro: " + t.getMessage());
      }
    });
  }

  public void goToMainActivity(Usuario usuario) {
    Intent mainActivity = new Intent(this, MainActivity.class);
    Bundle bundle = new Bundle();

    bundle.putSerializable("usuario", usuario);
    mainActivity.putExtras(bundle);
    startActivity(mainActivity, null);
  }

  public void cadastro(View view) {
    Intent i = new Intent(this, CadastroContaActivity.class);
    startActivity(i, null);
  }

  public void showError() {
    Toast.makeText(this, R.string.error_user_passwd, Toast.LENGTH_LONG).show();
  }
}
