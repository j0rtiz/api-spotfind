package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Filme;

public class DetalheCompraActivity extends AppCompatActivity {

  // variaveis de classe
  Compras compras;
  Filme filme;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);
    compras = (Compras)getIntent().getExtras().get("compras");

    int idFilme =  compras.getId();



    TextView titulo = findViewById(R.id.txtTitulo);
    TextView ano = findViewById(R.id.txtAno);
    TextView genero = findViewById(R.id.txtGenero);
    TextView atores = findViewById(R.id.txtAtores);
    TextView imdb = findViewById(R.id.txtImdbId);
    TextView local = findViewById(R.id.txtLocal);
    TextView valor = findViewById(R.id.txtValor);

    titulo.setText(filme.getTitulo());
    ano.setText(filme.getAno());
    genero.setText(filme.getGenero());
    atores.setText(filme.getAtores());
    imdb.setText(filme.getImbId());
    local.setText(filme.getLocal());
    valor.setText(filme.getValor().toString());

  }

  public void deletarCarro(View view) {

//    bancoDados.deletaCarro(filme.id);
    finish();
  }
}
