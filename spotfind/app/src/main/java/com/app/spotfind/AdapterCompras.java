package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCompras extends BaseAdapter {

  ArrayList<Ingresso> lista;
  Activity activity;

  public AdapterCompras(ArrayList<Ingresso> listaCompras, Activity activity) {
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
    // aqui e a ligacao entre ingresso e o item_carro
    Ingresso ingresso = lista.get(i);
    view = activity.getLayoutInflater().inflate(R.layout.item_compras, viewGroup, false);
    TextView idEvento = view.findViewById(R.id.textViewidEvento);
    TextView nomeEvento = view.findViewById(R.id.textViewNomeEvento);
    TextView valorEvento = view.findViewById(R.id.textViewValorEvento);

    Ingresso ing = new Ingresso();
    idEvento.setText(ing.getId()); //id sessao
    nomeEvento.setText(ing.getTitulo());
    valorEvento.setText(ing.getValor());

    return view;
  }
}
