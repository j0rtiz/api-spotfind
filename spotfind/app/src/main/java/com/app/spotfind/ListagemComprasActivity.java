package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListagemComprasActivity extends AppCompatActivity {
  ArrayList<Ingresso> listaCompras;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);


    listaCompras = (ArrayList<Ingresso>) getIntent().getExtras().get("lista");

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras, this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras, this));
  }


  // essa funcao, vai ser executada, toda vez que a activy for visualizada
  @Override
  protected void onResume() {
    super.onResume();
//    listaCompras = banco.buscaCompras();

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras, this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras, this));

  }

}
