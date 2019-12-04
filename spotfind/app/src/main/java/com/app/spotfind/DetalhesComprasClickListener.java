package com.app.spotfind;

import android.app.Activity;
import android.widget.AdapterView;

import com.app.spotfind.Models.Compras;

import java.util.List;

import retrofit2.Callback;

public abstract class DetalhesComprasClickListener implements AdapterView.OnItemClickListener {

  Compras minhaLista;
  Activity activity;

  public DetalhesComprasClickListener(Compras lista,
                                      Callback<List<Compras>> activity){
    minhaLista = lista;
    this.activity = (Activity) activity;
  }

//  @Override
//  public void onItemClick(AdapterView<?> adapterView,
//                          View view, int i, long l) {
//    // aqui eu vou abrir um activity, com informacoes
//    // detalhadas do compras.
//    Compras compras = minhaLista.get(i);
//    Intent intent = new Intent(activity,
//      DetalheCompraActivity.class);
//
//    Bundle bundle = new Bundle();
//    bundle.putSerializable("compras",compras);
//    intent.putExtras(bundle);
//    activity.startActivity(intent);
//  }
}
