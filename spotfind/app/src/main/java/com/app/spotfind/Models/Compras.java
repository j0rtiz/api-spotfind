package com.app.spotfind.Models;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Compras implements Serializable {
  String imdbId;
  String valor;
  int id;

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
  }

  String usuarioId;

}
