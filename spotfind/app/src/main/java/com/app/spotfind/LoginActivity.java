package com.app.spotfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    JSON
  }

  public void Login(View view) {

    TextView usuario = findViewById(R.id.textName);
    TextView senha = findViewById(R.id.textSenha);


    ClientHttp clientHttp = new ClientHttp();
    try {
      String response = clientHttp.executeOnExecutor(Executors.newSingleThreadExecutor(),
        new String[]{"https://api-spotfind.herokuapp.com/api/Compras?access_token=NtBJfCjGEhNGu2xvq4or62FLDlo3Hhqug40AM41w8m1cn5wwzurBHr5fEt3LBGWm"}).get();
      Log.d("response", response);
      // TextView textView = findViewById(R.id.textViewMeuNome);
      //textView.setText(ret);
      System.out.print(response);

    } catch (ExecutionException e) {
      e.printStackTrace();
      Toast.makeText(this, "Erro ao efetuar requisição", Toast.LENGTH_LONG).show();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Toast.makeText(this, "Timeout", Toast.LENGTH_LONG).show();
    }


  }
}
