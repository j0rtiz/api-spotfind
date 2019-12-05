package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmaCompraActivity extends AppCompatActivity {
  Sessoes sessoes;

  String imdbId, usuarioId, titulo, valor, local;

  Compras filmeComprado;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirma_compra);
    setTitle("Realizar Compra");

    Fresco.initialize(this);

    TextView tituloCofirma = findViewById(R.id.tituloConfirmaTxt);
    TextView valorCofirma = findViewById(R.id.valorConfirmaTxt);
    TextView localConfirma = findViewById(R.id.localConfirmaTxt);
    final SimpleDraweeView imgPoster = findViewById(R.id.PosterConfImageView);

    sessoes = (Sessoes) getIntent().getExtras().get("filmeParaCompra");

    imdbId = sessoes.getImdbId();
    usuarioId = sessoes.getUsuarioId();

    titulo = sessoes.getTitulo();
    valor = sessoes.getValor();
    local = sessoes.getLocal();

    Uri uri = Uri.parse(sessoes.getPoster());
    imgPoster.setImageURI(uri);

    tituloCofirma.setText(titulo);
    valorCofirma.setText("R$ "+valor+",00");
    localConfirma.setText(local);
  }

  public void ConfirmaComprar(View view) {
    filmeComprado = new Compras();
    filmeComprado.setImdbId(imdbId);
    filmeComprado.setValor(valor);
    filmeComprado.setUsuarioId(usuarioId);
    filmeComprado.setId(null);

    Call<Compras> call = new RetrofitConfig().getComprasService().postCompra(filmeComprado);
    call.enqueue(new Callback<Compras>() {
      @Override
      public void onResponse(Call<Compras> call, Response<Compras> response) {
        Compras c = response.body();

        try {
          if (c.getId() != null) {
            comprasListagem(Integer.parseInt(c.getUsuarioId()));
            sucesso();
          }
        } catch (Exception e) {
          Log.e("Erro Compra: ", "Erro: " + e.getMessage());
          showError();
        }
      }

      @Override
      public void onFailure(Call<Compras> call, Throwable t) {
        Log.e("Confirma Compra: ", "Erro: " + t.getMessage());
      }
    });

  }

  public void comprasListagem(int usuarioId) {
    Intent intentListagemComprasActivity = new Intent(this, ListagemComprasActivity.class);
    Bundle bundle = new Bundle();

    bundle.putSerializable("usuarioId", usuarioId);
    intentListagemComprasActivity.putExtras(bundle);
    startActivity(intentListagemComprasActivity);
  }

  public void sucesso(){
    Toast.makeText(this, R.string.compra_com_sucesso, Toast.LENGTH_LONG).show();
  }

  public void showError() {
    Toast.makeText(this, R.string.compra_nao, Toast.LENGTH_LONG).show();
  }
}
