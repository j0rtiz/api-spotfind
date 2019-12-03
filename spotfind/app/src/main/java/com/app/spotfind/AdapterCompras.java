package com.app.spotfind;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;

import java.util.ArrayList;

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
  public View getView(int i, View view, ViewGroup viewGroup) {
    // aqui e a ligacao entre compras e o item_carro
    Compras compras = lista.get(i);
    view = activity.getLayoutInflater().inflate(R.layout.item_compras, viewGroup, false);
    TextView idEvento = view.findViewById(R.id.textViewIdEvento);
    TextView nomeEvento = view.findViewById(R.id.textViewNomeEvento);
    TextView valorEvento = view.findViewById(R.id.textViewValorEvento);



    idEvento.setText(compras.getId()); //id sessao
//    nomeEvento.setText(compras.getTitulo());
//    valorEvento.setText(compras.getValor().toString());

    return view;
  }
}
