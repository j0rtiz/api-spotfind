package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheFilmeActivity extends AppCompatActivity {
  Sessoes sessoes;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Fresco.initialize(this);
    setContentView(R.layout.activity_detalhe_filme);
    sessoes = (Sessoes) getIntent().getExtras().get("sessao");

    final String imdbId = sessoes.getImdbId();
    String urlBuscaImbd = "Sessoes?filter[where][imdbId]=" + imdbId;

    final TextView titulo = findViewById(R.id.txtTituloF);
    final TextView ano = findViewById(R.id.txtAnoF);
    final TextView genero = findViewById(R.id.txtGeneroF);
    final TextView atores = findViewById(R.id.txtAtoresF);
    final TextView imdb = findViewById(R.id.txtImdbIdF);
    final TextView local = findViewById(R.id.txtLocalF);
    final TextView valor = findViewById(R.id.txtValorF);
    final SimpleDraweeView imgPoster = findViewById(R.id.imageViewCapaF);

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
        Uri uri = Uri.parse(s.getPoster());
        imgPoster.setImageURI(uri);
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
