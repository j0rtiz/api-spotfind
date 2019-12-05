package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroContaActivity extends AppCompatActivity {
  String user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_conta);

    setTitle("Cadastre Agora!");
  }

  public void cadastrar(View view) {

    EditText emailCad = findViewById(R.id.emailCadEditText3);
    EditText passCad = findViewById(R.id.senhaCadEditText);
    EditText nomeCad = findViewById(R.id.nomeCadEditText);

    if (emailCad.getText().toString().equals("")) {
      Toast.makeText(getApplicationContext(), R.string.preencha_email, Toast.LENGTH_LONG).show();
      ;
      return;
    }

    if (nomeCad.getText().toString().equals("")) {
      Toast.makeText(getApplicationContext(), R.string.preencha_nome, Toast.LENGTH_LONG).show();
      return;
    }

    if (passCad.getText().toString().equals("")) {
      Toast.makeText(getApplicationContext(), R.string.preencha_senha, Toast.LENGTH_LONG).show();
      return;
    }

    final Usuario usuario = new Usuario();
    usuario.setEmail(emailCad.getText().toString());
    usuario.setPassword(passCad.getText().toString());
    usuario.setNome(nomeCad.getText().toString());

    Call<Usuario> call = new RetrofitConfig().loginUsuarioService().postCadastroUsuario(usuario);
    call.enqueue(new Callback<Usuario>() {
      @Override
      public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        Usuario u = response.body();

        try {
          if (u.getUserId() != null) {
            user = u.getEmail().toString();
            voltarLogin(user);
            Toast.makeText(getApplicationContext(), "Cadastro Realizado!", Toast.LENGTH_LONG).show();
          }
        } catch (Exception e) {
          Log.e("Cadastro: ", "Erro: " + e.getMessage());
          showError();
        }
      }

      @Override
      public void onFailure(Call<Usuario> call, Throwable t) {
        Log.e("Cadastro: ", "Erro: " + t.getMessage());
      }
    });
  }

  public void voltarLogin(String email) {
    Intent login = new Intent(this, LoginActivity.class);
    Bundle bundle = new Bundle();

    if(email != null || !email.isEmpty()){
      bundle.putSerializable("usuarioNovo", email);
      login.putExtras(bundle);
    }

    startActivity(login, null);
  }

  public void jaTenhoConta(View view) {
    Intent login = new Intent(this, LoginActivity.class);
    startActivity(login, null);
  }

  public void showError() {
    Toast.makeText(this, R.string.erro_cadastrar, Toast.LENGTH_LONG).show();
  }

}
