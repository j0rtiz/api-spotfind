package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetalheCompraActivity extends AppCompatActivity {

  // variaveis de classe
//  BancoDados bancoDados ;
  Ingresso ingresso;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhe_compra);
    ingresso = (Ingresso)getIntent().getExtras().get("ingresso");

//    TextView nomeCarro = findViewById(R.id.textViewNomeCarro);
//    TextView corCarro = findViewById(R.id.textViewCorCarro);
//    TextView marcaCarro = findViewById(R.id.textViewMarcaCarro);
//    TextView placaCarro = findViewById(R.id.textViewPlacaCarro);
//
//    nomeCarro.setText(ingresso.getTitulo());
//    corCarro.setText(ingresso.cor);
//    marcaCarro.setText(ingresso.marca);
//    placaCarro.setText(ingresso.placa);

//    bancoDados = new BancoDados(this);




  }

  public void deletarCarro(View view) {

//    bancoDados.deletaCarro(ingresso.id);
    finish();
  }
}
