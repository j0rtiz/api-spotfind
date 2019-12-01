package com.app.spotfind.Models;

import java.io.Serializable;

public class Filme implements Serializable {

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getAtores() {
    return atores;
  }

  public void setAtores(String atores) {
    this.atores = atores;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getImbId() {
    return imbId;
  }

  public void setImbId(String imbId) {
    this.imbId = imbId;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
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

  private String titulo, ano, genero, atores, poster, imbId, tipo, local;
  private Integer id;
  private Double valor;

}
