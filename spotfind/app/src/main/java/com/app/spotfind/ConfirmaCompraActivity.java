package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.spotfind.Models.Compras;
import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Models.Usuario;
import com.app.spotfind.Network.RetrofitConfig;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmaCompraActivity extends AppCompatActivity {
  Sessoes sessoes;

  String imdbId, usuarioId, titulo, valor, local;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirma_compra);

    TextView tituloCofirma = findViewById(R.id.tituloConfirmaTxt);
    TextView valorCofirma = findViewById(R.id.valorConfirmaTxt);
    TextView localConfirma = findViewById(R.id.localConfirmaTxt);

    sessoes = (Sessoes) getIntent().getExtras().get("filmeParaCompra");

    imdbId = sessoes.getImdbId();
    usuarioId = sessoes.getUsuarioId();

    titulo = sessoes.getTitulo();
    valor = sessoes.getValor();
    local = sessoes.getLocal();


    tituloCofirma.setText(titulo);
    valorCofirma.setText(valor);
    localConfirma.setText(local);
  }

  public void ConfirmaComprar(View view) {

    final Compras filmeComprado = new Compras();

    filmeComprado.setImdbId(imdbId);
    filmeComprado.setValor(valor);
    filmeComprado.setUsuarioId(usuarioId);

    Call<Compras> call = new RetrofitConfig().getComprasService().doCompra(filmeComprado);
    call.enqueue(new Callback<Compras>() {
      @Override
      public void onResponse(Call<Compras> call, Response<Compras> response) {
        Compras c = response.body();

        try {
          if (filmeComprado.getImdbId() == c.getImdbId() ) {
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
