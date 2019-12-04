package com.app.spotfind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;

import java.util.ArrayList;

public class DetalhesFilmesClickListener implements AdapterView.OnItemClickListener {

  ArrayList<Sessoes> minhaLista;
  Activity activity;

  public DetalhesFilmesClickListener(ArrayList<Sessoes> lista,
                                     Activity activity){
    minhaLista = lista;
    this.activity = activity;
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView,
                          View view, int i, long l) {
    // aqui eu vou abrir um activity, com informacoes
    // detalhadas do sessoes.
    Sessoes sessoes = minhaLista.get(i);
    Intent intent = new Intent(activity,
      DetalheFilmeActivity.class);

    Bundle bundle = new Bundle();
    bundle.putSerializable("sessao",sessoes);
    intent.putExtras(bundle);
    activity.startActivity(intent);
  }
}
