package com.app.spotfind.Models;

import java.io.Serializable;

public class Usuario implements Serializable {

  private String password;
  private String email;
  private String userId;
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return fullName;
  }

  public void setNome(String nome) {
    fullName = nome;
  }

  private String fullName;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
