package com.cli.springx.service.impl;

import java.util.Scanner;

import com.cli.springx.service.InputOutput;

public class InputOutputImpl implements InputOutput {

  private Scanner scanner = new Scanner(System.in);

  @Override
  public void print(String message) {
    System.out.println(message);
  }

  @Override
  public void printError(String message) {
    System.err.println(message);
  }

  @Override
  public void printSuccess(String message) {
    System.out.println(message);
  }

  @Override
  public void printWarning(String message) {
    System.out.println(message);
  }

  @Override
  public void printInfo(String message) {
    System.out.println(message);
  }

  @Override
  public String readInput() {
    return scanner.nextLine();
  }

  @Override
  public Integer readIntInput() {
    return scanner.nextInt();
  }

}
