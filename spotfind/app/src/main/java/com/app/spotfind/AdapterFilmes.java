package com.app.spotfind;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterFilmes extends BaseAdapter {

  ArrayList<Sessoes> lista;
  Activity activity;

  public AdapterFilmes(ArrayList<Sessoes> listaSessoes, Activity activity) {
    this.lista = listaSessoes;
    this.activity = activity;

  }


  @Override
  public int getCount() {
    // select count(1) from Pessoas;
    return lista.size();
  }

  @Override
  public Object getItem(int i) {
    // select * from Pessoas where id_pessoa = 1;
    return lista.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(final int i, View view, ViewGroup viewGroup) {

    Sessoes filmes = lista.get(i);
    view = activity.getLayoutInflater().inflate(R.layout.item_filmes, viewGroup, false);

    final String imdbId = filmes.getImdbId();
    String urlBuscaImbd = "Sessoes?filter[where][imdbId]=" + imdbId;



    final TextView nomeEvento = view.findViewById(R.id.textTituloFilme);
    final TextView valorEvento = view.findViewById(R.id.textValorFilme);


    Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getFilmePorImdbId(urlBuscaImbd);
    call.enqueue(new Callback<List<Sessoes>>() {
      @Override
      public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {

        Sessoes s = new Sessoes();
        s = response.body().get(0);

        nomeEvento.setText(s.getTitulo());
        valorEvento.setText("R$ "+s.getValor() + " ,00");


      }

      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });

    return view;
  }
}
