package com.cli.springx.command;

import java.io.IOException;

import picocli.CommandLine.Command;

@Command(name = "generate:entity", description = "Generates a new entity")
public class GenerateEntityCommand implements Runnable {

  @Override
  public void run() {

  }

  private void generateEntity() throws IOException {
  }
}
