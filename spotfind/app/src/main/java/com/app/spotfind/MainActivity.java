package com.app.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotfind.Models.Usuario;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {

  private int usuarioId;
  private Usuario usuario;
  private String usuarioHash;

  private int idNum;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_main);
    setTitle("Início");

    usuario = (Usuario) getIntent().getExtras().get("usuario");
    usuarioId = Integer.parseInt(usuario.getUserId());

    usuarioHash = usuario.getId();
  }

  public void comprasListagem(View view) {
    Intent intentListagemComprasActivity = new Intent(this, ListagemComprasActivity.class);
    Bundle bundle = new Bundle();

    bundle.putSerializable("usuarioId", usuarioId);
    intentListagemComprasActivity.putExtras(bundle);
    startActivity(intentListagemComprasActivity);
  }

  public void filmesTop100Listagem(View view) {

    Intent intentFilmes = new Intent(this, ListagemFilmesActivity.class);

    Bundle bundle = new Bundle();

    bundle.putSerializable("usuarioId", usuarioId);
    intentFilmes.putExtras(bundle);
    startActivity(intentFilmes);

    startActivity(intentFilmes);
  }

  public void deletaConfirmaActivity(View view) {
    Intent deletaContaAc = new Intent(this, ConfirmaDeletaContaActivity.class);

    Bundle bundle = new Bundle();

    bundle.putSerializable("usuario", usuario);
    deletaContaAc.putExtras(bundle);

    startActivity(deletaContaAc);
  }

  public void voltarLogin(View view) {
    usuario = null;

    Intent sair = new Intent(this, LoginActivity.class);
    startActivity(sair);
  }


}
