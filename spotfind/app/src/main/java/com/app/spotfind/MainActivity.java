package com.app.spotfind;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotfind.Models.Sessoes;
import com.app.spotfind.Network.RetrofitConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        final SimpleDraweeView imgPoster = findViewById(R.id.imgPoster);
        final TextView txtTitulo = findViewById(R.id.txtTitulo);
        final TextView txtAno = findViewById(R.id.txtAno);
        final TextView txtGenero = findViewById(R.id.txtGenero);
        final TextView txtAtores = findViewById(R.id.txtAtores);
        final TextView txtImdbId = findViewById(R.id.txtImdbId);
        final TextView txtTipo = findViewById(R.id.txtTipo);
        final TextView txtLocal = findViewById(R.id.txtLocal);
        final TextView txtValor = findViewById(R.id.txtValor);

        Call<List<Sessoes>> call = new RetrofitConfig().getSessoesService().getSessoes();
        call.enqueue(new Callback<List<Sessoes>>() {
            @Override
            public void onResponse(Call<List<Sessoes>> call, Response<List<Sessoes>> response) {
                System.out.println("Sessoes: " + response.body());
                if (response.body() != null) {
                    for (Sessoes sessoes : response.body()) {
                        Uri uri = Uri.parse(sessoes.getPoster());
                        imgPoster.setImageURI(uri);
                        txtTitulo.setText("Titulo: " + sessoes.getTitulo());
                        txtAno.setText("Ano: " + sessoes.getAno());
                        txtGenero.setText("Genero: " + sessoes.getGenero());
                        txtAtores.setText("Atores: " + sessoes.getAtores());
                        txtImdbId.setText("Codigo IMDB: " + sessoes.getImdbId());
                        if (sessoes.getTipo().equals("movie")) {
                            txtTipo.setText("Tipo: Filme");
                        } else {
                            txtTipo.setText("Tipo: " + sessoes.getTipo());
                        }
                        txtLocal.setText("Local: " + sessoes.getLocal());
                        txtValor.setText("Valor: R$ " + sessoes.getValor() + ",00");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sessoes>> call, Throwable t) {
                Log.e("Sessoes: ", "Erro: " + t.getMessage());
            }
        });
    }
}
