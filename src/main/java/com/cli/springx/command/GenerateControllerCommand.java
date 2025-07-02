package com.cli.springx.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.cli.springx.SpringXCli;
import com.cli.springx.service.InputOutput;
import com.cli.springx.util.FsUtils;
import com.cli.springx.util.TemplateRenderer;

import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "generate:controller", description = "Generates a controller class")
public class GenerateControllerCommand implements Runnable {

  private String baseSrcDir = "src/main/java";
  private String basePackage = "com.cli.springx";

  private InputOutput inputOutput;
  @CommandLine.ParentCommand
  private SpringXCli parent;

  public GenerateControllerCommand() {
    // Constructeur vide requis par Picocli
  }

  public GenerateControllerCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    commandLineUsage();
  }

  private InputOutput getInputOutput() {
    return parent != null ? parent.getIo() : inputOutput;
  }

  private void commandLineUsage() {
    InputOutput inputOutput = getInputOutput();
    inputOutput.print("\u001B[32m> Entity name :\u001B[0m");
    String entityName = inputOutput.readInput().trim();
    if (entityName.isEmpty()) {
      inputOutput.printError("\u001B[31mEntity name can't be empty.\u001B[0m");
      return;
    }

    try {
      String entityDir = FsUtils.preparePackagePath(baseSrcDir, basePackage, "controller");
      generateController(entityName, entityDir);
      inputOutput.print("🎉\u001B[32m Controller " + entityName + " generated successfully!\u001B[0m🎉");
    } catch (IOException e) {
      inputOutput.printError("❌\u001B[31mError while generating controller: " + e.getMessage() + "\u001B[0m");
    }
  }

  private void generateController(String entityName, String entityDir) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String controllerCode = renderer.renderControllerTemplate(entityName, basePackage);
    Files.writeString(Path.of(entityDir + "/" + entityName + "Controller.java"), controllerCode);
  }
}
