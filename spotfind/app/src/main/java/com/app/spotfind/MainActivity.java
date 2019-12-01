package com.app.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView txtTitulo = findViewById(R.id.txtTitulo);
    final TextView txtAno = findViewById(R.id.txtAno);
    final TextView txtGenero = findViewById(R.id.txtGenero);
    final TextView txtAtores = findViewById(R.id.txtAtores);
//        final ImageView imgPoster = findViewById(R.id.imgPoster);
    final TextView txtImdbId = findViewById(R.id.txtImdbId);
    final TextView txtTipo = findViewById(R.id.txtTipo);
    final TextView txtLocal = findViewById(R.id.txtLocal);
    final TextView txtValor = findViewById(R.id.txtValor);
//        final TextView txtId = findViewById(R.id.txtId);

    Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getSessoes();
    call.enqueue(new Callback<List<Sessoes>>() {
      @Override
      public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {
        for (Sessoes sessoes : response.body()) {
          System.out.print(sessoes);
          txtTitulo.setText(sessoes.getTitulo());
          txtAno.setText(sessoes.getAno());
          txtGenero.setText(sessoes.getGenero());
          txtAtores.setText(sessoes.getAtores());
//                imgPoster.setText(sessoes.getPoster());
          txtImdbId.setText(sessoes.getImdbId());
          txtTipo.setText(sessoes.getTipo());
          txtLocal.setText(sessoes.getLocal());
          txtValor.setText(sessoes.getValor());
//                    txtId.setText(sessoes.getId());
        }
      }

      @Override
      public void onFailure(Call<List<Sessoes>> call, Throwable t) {
        System.out.print(t.toString());
      }
    });
  }

  public void listagemComprasBotao (View view){
    Intent intent = new Intent(this,ListagemComprasActivity.class);
    Bundle bundle = new Bundle();

    int usuarioId = 0 ; //aqui tu coloca como pega o usuário logado

    bundle.putSerializable("usuario",usuarioId); //Passe o ID do usuario para achar as compras na listagem compras
    intent.putExtras(bundle);
    startActivity(intent);
  }
}
