package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemComprasActivity extends AppCompatActivity {
  ArrayList<Compras> listaCompras ;
  Compras comprasAct;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listagem_compras);

    int usuario = (int)
      getIntent().getExtras().get("usuario");

    String u = Integer.toString(usuario);

    String request = "%7B/where/%3A%7B/usuarioId/%3A"+u+"%7D%7D";


//    METODO PARA CHAMAR AS COMPRAS POR USUARIO. como nao funcionou, coloquei um metodo pra trazer todas as compras para testar
//    Call<List<Compras>> call = new RetrofitConfig().getComprasService().getComprasPorUsuarioId(request);
    Call<List<Compras>> call = new RetrofitConfig().getComprasService().getTodasCompras();

    call.enqueue(new Callback<List<Compras>>() {
      @Override
      public void onResponse(Call<List<Compras>> call, Response<List<Compras>> response) {
        if (response.body() != null) {
          System.out.println(response.body());
          for (Compras retCompras : response.body()) {
            System.out.print("RETORNOU = " + retCompras.getImdbId());

//            comprasAct.setId(retCompras.getId());
//            comprasAct.setImbdId(retCompras.getImbdId());
//            comprasAct.setValor(retCompras.getValor());
//            comprasAct.setUsuarioId(retCompras.getUsuarioId());

//            listaCompras.add(comprasAct);
            listaCompras.add(retCompras);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Compras>> call, Throwable t) {
        Log.e("Compras: ", "Erro: " + t.getMessage());
      }
    });

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras,this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras,this));

  }

  // essa funcao, vai ser executada, toda vez que a activy for visualizada
  @Override
  protected void onResume() {
    super.onResume();
//    listaCompras = banco.buscaCarros();

    AdapterCompras adapterCompras = new AdapterCompras(listaCompras,this);
    ListView listView = findViewById(R.id.listViewListagemCompras);
    listView.setAdapter(adapterCompras);
    listView.setOnItemClickListener(new
      DetalhesComprasClickListener(listaCompras,this));

  }
}
