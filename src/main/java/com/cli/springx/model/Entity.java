package com.cli.springx.model;

import java.util.ArrayList;
import java.util.List;

public class Entity {

  private String name;

  private List<Attributs> attributs = new ArrayList<>();

  public Entity() {
  }

  public Entity(String name) {
    this.name = name;
  }

  public Entity(String name, List<Attributs> attributs) {
    this.name = name;
    this.attributs = attributs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Attributs> getAttributs() {
    return attributs;
  }

  public void setAttributs(List<Attributs> attributs) {
    this.attributs = attributs;
  }

  public void addAttributs(Attributs attribut) {
    this.attributs.add(attribut);
  }

}
