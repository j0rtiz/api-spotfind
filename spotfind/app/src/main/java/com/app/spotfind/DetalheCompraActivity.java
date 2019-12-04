package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheCompraActivity extends AppCompatActivity {

  // variaveis de classe
  Compras compras;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_detalhe_compra);
    compras = (Compras) getIntent().getExtras().get("compras");

    final String imdbId = compras.getImdbId();
    String urlBuscaImbd = "Sessoes?filter[where][imdbId]=" + imdbId;

    final TextView titulo = findViewById(R.id.txtTitulo);
    final TextView ano = findViewById(R.id.txtAno);
    final TextView genero = findViewById(R.id.txtGenero);
    final TextView atores = findViewById(R.id.txtAtores);
    final TextView imdb = findViewById(R.id.txtImdbId);
    final TextView local = findViewById(R.id.txtLocal);
    final TextView valor = findViewById(R.id.txtValor);
    final SimpleDraweeView imgPoster = findViewById(R.id.imageViewCapa);

    Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getFilmePorImdbId(urlBuscaImbd);
    call.enqueue(new Callback<List<Sessoes>>() {
      @Override
      public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {

        Sessoes s = new Sessoes();
        s = response.body().get(0);
        titulo.setText(s.getTitulo());
        ano.setText(s.getAno());
        genero.setText(s.getGenero());
        atores.setText(s.getAtores());
        imdb.setText(s.getImdbId());
        local.setText(s.getLocal());
        valor.setText(s.getValor());
//        Uri uri = Uri.parse(s.getPoster());
//        imgPoster.setImageURI(uri);
      }

      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });
  }

  public void deletaFilme(View view) {
    finish();
  }
}
