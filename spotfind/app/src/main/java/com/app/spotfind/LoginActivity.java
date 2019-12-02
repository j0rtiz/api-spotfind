package com.app.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void Login(View view) {

        TextView userEmail = findViewById(R.id.textEmail);
        TextView password = findViewById(R.id.textSenha);

        final Usuario usuario = new Usuario();
        usuario.setEmail(userEmail.getText().toString());
        usuario.setPassword(password.getText().toString());


        Call<Usuario> call = new RetrofitConfig().loginUsuarioService().loginUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario login = response.body();

                try {
                    if (login.getUserId() != null) {
                        goToMainActivity();
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

    public void goToMainActivity() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity, null);
    }

    public void showError () {
        Toast.makeText(this, R.string.error_user_passwd, Toast.LENGTH_LONG).show();
    }
}
