package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemComprasActivity extends AppCompatActivity {
  ArrayList<Compras> listaCompras ;
  Compras comprasAct;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);

    int usuario = (int)
      getIntent().getExtras().get("usuario");

//    listaCompras = (ArrayList<Compras>) getIntent().getExtras().get("lista");

    String request = "%7B/where/%3A%7B/usuarioId/%3A"+usuario+"%7D%7D";

    Call<List<Compras>> call = new RetrofitConfig().getComprasPorId().getComprasPorUsuarioId(request);
    call.enqueue(new Callback<List<Compras>>() {
      @Override
      public void onResponse(Call<List<Compras>> call, Response<List<Compras>> response) {
        if (response.body() != null) {
          for (Compras retCompras : response.body()) {
            System.out.print(retCompras);

            comprasAct.setId(retCompras.getId());
            comprasAct.setImbdId(retCompras.getImbdId());
            comprasAct.setValor(retCompras.getValor());
            comprasAct.setUsuarioId(retCompras.getUsuarioId());

            listaCompras.add(comprasAct);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Compras>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras,this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras,this));


  }

  // essa funcao, vai ser executada, toda vez que a activy for visualizada
  @Override
  protected void onResume() {
    super.onResume();
//    listaCompras = banco.buscaCarros();

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras,this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras,this));

  }
}
