package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

public class DetalheFilmeActivity extends AppCompatActivity {
  Sessoes sessoes;

  TextView titulo = null;
  TextView ano= null;
  TextView genero= null;
  TextView atores= null;
  TextView imdb= null;
  TextView local= null;
  TextView valor= null;
  SimpleDraweeView imgPoster= null;

  String usuarioId;
  String posterParaCompra;

  String valorDeCompra;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Fresco.initialize(this);

    setContentView(R.layout.activity_detalhe_filme);

    titulo = findViewById(R.id.txtTituloF);
    ano = findViewById(R.id.txtAnoF);
    genero = findViewById(R.id.txtGeneroF);
    atores = findViewById(R.id.txtAtoresF);
    imdb = findViewById(R.id.txtImdbIdF);
    local = findViewById(R.id.txtLocalF);
    valor = findViewById(R.id.txtValorF);
    final SimpleDraweeView imgPoster = findViewById(R.id.imageViewCapaF);



    sessoes = (Sessoes) getIntent().getExtras().get("sessao");

    final String imdbId = sessoes.getImdbId();
    String urlBuscaImbd = "Sessoes?filter[where][imdbId]=" + imdbId;

    usuarioId = sessoes.getUsuarioId();

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
        valor.setText("R$ "+s.getValor()+",00");
        Uri uri = Uri.parse(s.getPoster());
        imgPoster.setImageURI(uri);

        posterParaCompra = s.getPoster();

        valorDeCompra = s.getValor();
      }

      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });
  }

  public void comprarFilme(View view) {
    Intent intent = new Intent(this, ConfirmaCompraActivity.class);
    Bundle bundle = new Bundle();

    Sessoes s = new Sessoes();
    s.setImdbId(imdb.getText().toString());

    s.setValor(valorDeCompra);

    s.setUsuarioId(usuarioId);

    s.setTitulo(titulo.getText().toString());
    s.setLocal(local.getText().toString());
    s.setPoster(posterParaCompra);

    bundle.putSerializable("filmeParaCompra", s);
    intent.putExtras(bundle);
    startActivity(intent);
  }
}
