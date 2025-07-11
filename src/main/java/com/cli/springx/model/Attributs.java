package com.cli.springx.model;

public class Attributs {

  private String name;
  private String type;
  private boolean isId;

  public Attributs() {
  }

  public Attributs(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public Attributs(String name, String type, boolean isId) {
    this.name = name;
    this.type = type;
    this.isId = isId;
  }

  public boolean isId() {
    return isId;
  }

  public void setId(boolean isId) {
    this.isId = isId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
