package com.cli.springx.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.cli.springx.service.InputOutput;
import com.cli.springx.util.TemplateRenderer;

import picocli.CommandLine.Command;

@Command(name = "generate:controller", description = "Generates a controller class")
public class GenerateControllerCommand implements Runnable {

  private InputOutput inputOutput;

  public GenerateControllerCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    commandLineUsage();
  }

  private void commandLineUsage() {
  }

  private void generateController(String entityName) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String basePackage = "com.cli.springx";
    String controllerCode = renderer.renderControllerTemplate(entityName, basePackage);
    Files.writeString(Path.of(entityName + "/" + entityName + "Controller.java"), controllerCode);
  }

}
