package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemFilmesActivity extends AppCompatActivity {

  ArrayList<Sessoes> listaSessoes = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_filmes);

    final Activity a = this;

    Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getSessoes();
    call.enqueue(new Callback<List<Sessoes>>() {
      @Override
      public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {
        if (response.body() != null) {
          for (Sessoes sessoes : response.body()) {

            listaSessoes.add(sessoes);
          }

          AdapterFilmes adapterFilmes = new AdapterFilmes(listaSessoes, a);
          ListView listView = findViewById(R.id.listViewListagemFilmes);
          listView.setAdapter(adapterFilmes);
          listView.setOnItemClickListener(new DetalhesFilmesClickListener(listaSessoes, a));

        }
      }

      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        Log.e("Filmes: ", "Erro: " + t.getMessage());
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
//    listaCompras = banco.buscaCarros();

    AdapterFilmes adapterFilmes = new AdapterFilmes(listaSessoes, this);
    ListView listView = findViewById(R.id.listViewListagemFilmes);
    listView.setAdapter(adapterFilmes);
    listView.setOnItemClickListener(new
      DetalhesFilmesClickListener(listaSessoes, this));

  }
}
