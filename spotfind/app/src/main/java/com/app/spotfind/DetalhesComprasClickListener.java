package com.app.spotfind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.app.spotfind.Models.Ingresso;

import java.util.ArrayList;

public class DetalhesComprasClickListener implements AdapterView.OnItemClickListener {

  ArrayList<Ingresso> minhaLista;
  Activity activity;

  public DetalhesComprasClickListener(ArrayList<Ingresso> lista,
                                    Activity activity){
    minhaLista = lista;
    this.activity = activity;
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView,
                          View view, int i, long l) {
    // aqui eu vou abrir um activity, com informacoes
    // detalhadas do ingresso.
    Ingresso ingresso = minhaLista.get(i);
    Intent intent = new Intent(activity,
      DetalheCompraActivity.class);

    Bundle bundle = new Bundle();
    bundle.putSerializable("ingresso",ingresso);
    intent.putExtras(bundle);
    activity.startActivity(intent);

  }
}
