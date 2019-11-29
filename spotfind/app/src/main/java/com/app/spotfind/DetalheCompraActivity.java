package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.spotfind.Models.Ingresso;

public class DetalheCompraActivity extends AppCompatActivity {

  // variaveis de classe
//  BancoDados bancoDados ;
  Ingresso ingresso;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);
    ingresso = (Ingresso)getIntent().getExtras().get("ingresso");

    TextView titulo = findViewById(R.id.textViewDetalheTitulo);
    TextView ano = findViewById(R.id.textViewDetalheAno);
    TextView genero = findViewById(R.id.textViewDetalheGenero);
    TextView atores = findViewById(R.id.textViewDetalheAtores);
    TextView imdb = findViewById(R.id.textViewDetalheImdbId);
    TextView local = findViewById(R.id.textViewDetalheLocal);
    TextView valor = findViewById(R.id.textViewDetalheValor);

    titulo.setText(ingresso.getTitulo());
    ano.setText(ingresso.getAno());
    genero.setText(ingresso.getGenero());
    atores.setText(ingresso.getAtores());
    imdb.setText(ingresso.getImbId());
    local.setText(ingresso.getLocal());
    valor.setText(ingresso.getValor());

  }

  public void deletarCarro(View view) {

//    bancoDados.deletaCarro(ingresso.id);
    finish();
  }
}
