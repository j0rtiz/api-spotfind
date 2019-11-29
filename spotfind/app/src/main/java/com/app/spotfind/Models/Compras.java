package com.app.spotfind.Models;

import android.content.Intent;

public class Compras {

    String imbdId;
    Double valor;
    Integer id;

  public String getImbdId() {
    return imbdId;
  }

  public void setImbdId(String imbdId) {
    this.imbdId = imbdId;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
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

  Integer usuarioId;

}
