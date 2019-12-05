package com.app.spotfind;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemComprasActivity extends AppCompatActivity {
  ArrayList<Compras> listaCompras = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);
    setTitle("Minhas Compras");


    final Activity a = this;

    int usuarioId = (int) getIntent().getExtras().get("usuarioId");
    String url = "Compras?filter[where][usuarioId]=" + usuarioId;

    Call<List<Compras>> call = new RetrofitConfig().getComprasService().getComprasPorUsuario(url);
    call.enqueue(new Callback<List<Compras>>() {
      @Override
      public void onResponse(Call<List<Compras>> call, Response<List<Compras>> response) {
        if (response.body() != null) {
          for (Compras compras : response.body()) {

            listaCompras.add(compras);
          }

          AdapterCompras adapterCompras = new AdapterCompras(listaCompras, a);
          ListView listView = findViewById(R.id.listViewListagemCompras);
          listView.setAdapter(adapterCompras);
          listView.setOnItemClickListener(new DetalhesComprasClickListener(listaCompras, a));

        }
      }

      @Override
      public void onFailure(Call<List<Compras>> call, Throwable t) {
        Log.e("Compras: ", "Erro: " + t.getMessage());
      }
    });
  }

  // essa funcao, vai ser executada, toda vez que a activy for visualizada
  @Override
  protected void onResume() {
    super.onResume();
//    listaCompras = banco.buscaCarros();

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras, this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras, this));


  }
}
