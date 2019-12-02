package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Filme;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheCompraActivity extends AppCompatActivity {

  // variaveis de classe
  Compras compras;
  Filme filme;
  Uri uri;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_listagem_compras);
    compras = (Compras)getIntent().getExtras().get("compras");

    String idFilme =  compras.getImbdId();

    TextView titulo = findViewById(R.id.txtTitulo);
    TextView ano = findViewById(R.id.txtAno);
    TextView genero = findViewById(R.id.txtGenero);
    TextView atores = findViewById(R.id.txtAtores);
    TextView imdb = findViewById(R.id.txtImdbId);
    TextView local = findViewById(R.id.txtLocal);
    TextView valor = findViewById(R.id.txtValor);
    final ImageView imgPoster = findViewById(R.id.imageViewCapa);

    Call<List<Filme>> call = new RetrofitConfig().getSessoesService().getFilmePorImdbId(idFilme);
    call.enqueue(new Callback<List<Filme>>() {
      @Override
      public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
        for (Filme retFilme : response.body()) {
          System.out.print(retFilme);
          filme.setTitulo(retFilme.getTitulo());
          filme.setAno(retFilme.getAno());
          filme.setGenero(retFilme.getGenero());
          filme.setAtores(retFilme.getAtores());
          filme.setImdbId(retFilme.getImdbId());
          filme.setLocal(retFilme.getLocal());
          filme.setValor(retFilme.getValor());
          uri = Uri.parse(retFilme.getPoster());
        }
      }

      @Override
      public void onFailure(Call<List<Filme>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });

    titulo.setText(filme.getTitulo());
    ano.setText(filme.getAno());
    genero.setText(filme.getGenero());
    atores.setText(filme.getAtores());
    imdb.setText(filme.getImdbId());
    local.setText(filme.getLocal());
    valor.setText(filme.getValor().toString());
    imgPoster.setImageURI(uri);


  }

  public void deletarCarro(View view) {

//    bancoDados.deletaCarro(filme.id);
    finish();
  }
}
