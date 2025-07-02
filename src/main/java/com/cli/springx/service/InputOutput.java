package com.cli.springx.service;

public interface InputOutput {

  void print(String message);

  void printError(String message);

  void printSuccess(String message);

  void printWarning(String message);

  void printInfo(String message);

  String readInput();

  Integer readIntInput();
}
