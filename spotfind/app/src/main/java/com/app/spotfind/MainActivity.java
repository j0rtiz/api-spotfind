package com.app.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {

    private int usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        usuarioId = (int) getIntent().getExtras().get("usuarioId");
    }

    public void comprasListagem(View view) {
        Intent intentListagemComprasActivity = new Intent(this, ListagemComprasActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("usuarioId", usuarioId);
        intentListagemComprasActivity.putExtras(bundle);
        startActivity(intentListagemComprasActivity);
    }
}
