package com.app.spotfind;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ClientHttp extends AsyncTask<String, Void, String> {

  private String method = "GET";
  public void setMethod(String method){
    this.method = method;
  }

  private String sendHTTPData(String URL) throws MalformedURLException {
    URL url = new URL(URL);
    int codigoResposta;
    HttpURLConnection conexao = null;
    InputStream is = null;
    String retornoString = "";
    try {

      conexao = (HttpURLConnection) url.openConnection();
      conexao.setDoOutput(true);
      conexao.setDoInput(true);
      conexao.setRequestMethod("GET");
      conexao.setRequestProperty("Content-Type", "application/json");
      conexao.setRequestProperty("Accept", "application/json");
      conexao.connect();



      codigoResposta = conexao.getResponseCode();
      if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
        is = conexao.getInputStream();
      } else {
        is = conexao.getErrorStream();
      }
      retornoString = converterInputStreamToString(is);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      ;
      conexao.disconnect();
    }

    return retornoString;
  }



  private String converterInputStreamToString(InputStream is) throws Exception {
    StringBuffer buffer = new StringBuffer();

    BufferedReader br = null;
    String linha;
    try {
      br = new BufferedReader(new InputStreamReader(is));
      while ((linha = br.readLine()) != null) {
        buffer.append(linha);
      }
    } finally {
      br.close();
    }

    return buffer.toString();
  }

  @Override
  protected String doInBackground(String... params) {
    String ret = "";

    try {
      ret =  sendHTTPData(params[0]);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return ret;

  }

}
