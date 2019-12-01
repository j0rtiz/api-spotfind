package com.app.spotfind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.app.spotfind.Models.Compras;

import java.util.ArrayList;

public class DetalhesComprasClickListener implements AdapterView.OnItemClickListener {

  ArrayList<Compras> minhaLista;
  Activity activity;

  public DetalhesComprasClickListener(ArrayList<Compras> lista,
                                    Activity activity){
    minhaLista = lista;
    this.activity = activity;
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView,
                          View view, int i, long l) {
    // aqui eu vou abrir um activity, com informacoes
    // detalhadas do compras.
    Compras compras = minhaLista.get(i);
    Intent intent = new Intent(activity,
      DetalheCompraActivity.class);

    Bundle bundle = new Bundle();
    bundle.putSerializable("compras",compras);
    intent.putExtras(bundle);
    activity.startActivity(intent);
  }
}
