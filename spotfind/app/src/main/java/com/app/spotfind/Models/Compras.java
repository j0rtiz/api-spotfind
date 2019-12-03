package com.app.spotfind.Models;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Compras implements Serializable {

    String imbdId;
    Integer id, usuarioId, valor;


  public String getImbdId() {
    return imbdId;
  }

  public void setImbdId(String imbdId) {
    this.imbdId = imbdId;
  }

  public Integer getValor() {
    return valor;
  }

  public void setValor(Integer valor) {
    this.valor = valor;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }

}
