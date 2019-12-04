package com.app.spotfind;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCompras extends BaseAdapter {

  ArrayList<Compras> lista;
  Activity activity;

  public AdapterCompras(ArrayList<Compras> listaCompras, Activity activity) {
    this.lista = listaCompras;
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
    // aqui e a ligacao entre compras e o item_carro
    Compras compras = lista.get(i);
    view = activity.getLayoutInflater().inflate(R.layout.item_compras, viewGroup, false);

    final String imdbId = compras.getImdbId();
    String urlBuscaImbd = "Sessoes?filter=[where][imdbId]=" + imdbId;

    final TextView idEvento = view.findViewById(R.id.textViewIdEvento);
    final TextView nomeEvento = view.findViewById(R.id.textViewNomeEvento);
    final TextView valorEvento = view.findViewById(R.id.textViewValorEvento);

    idEvento.setText(Integer.toString(compras.getId())); //id compra


    Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getFilmePorImdbId(urlBuscaImbd);
    call.enqueue(new Callback<List<Sessoes>>() {
      @Override
      public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {

        Sessoes s = new Sessoes();
        s = response.body().get(i);

        nomeEvento.setText(s.getTitulo());
        valorEvento.setText(s.getValor());

      }


      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });

    return view;
  }
}
