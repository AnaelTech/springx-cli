package com.cli.springx.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import com.cli.springx.service.InputOutput;
import com.cli.springx.util.FsUtils;
import com.cli.springx.util.PackageDetector;
import com.cli.springx.util.TemplateRenderer;

import com.cli.springx.SpringXCli;

import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "generate:service", description = "Generates a new service class")
public class GenerateServiceCommand implements Runnable {
  private InputOutput inputOutput;
  private String baseSrcDir = "src/main/java";
  private String basePackage;

  @CommandLine.ParentCommand
  private SpringXCli parent;

  public GenerateServiceCommand() {
    // Constructeur vide requis par Picocli
  }

  public GenerateServiceCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    this.basePackage = PackageDetector.detectBasePackage();
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
    inputOutput.print("\u001B[32m> Generate ServiceImpl? (y/n):\u001B[0m");
    String generateServiceImpl = inputOutput.readInput().trim();
    if (generateServiceImpl.equalsIgnoreCase("y")) {
      inputOutput.print("✅\u001B[32m ServiceImpl generated!\u001B[0m");
    } else {
      inputOutput.print("❌\u001B[33m ServiceImpl not generated!\u001B[0m");
    }
    try {
      String entityDir = FsUtils.preparePackagePath(baseSrcDir, basePackage, "service");
      generateService(entityName, entityDir);
      if (generateServiceImpl.equalsIgnoreCase("y")) {
        entityDir = FsUtils.preparePackagePath(baseSrcDir, basePackage, "service/impl");
        generateServiceImpl(entityName, entityDir);
      }
      inputOutput.print("🎉\u001B[32m Service " + entityName + " generated successfully!\u001B[0m🎉");
    } catch (IOException e) {
      inputOutput.printError("❌\u001B[31mError while generating service: " + e.getMessage() + "\u001B[0m");
    }
  }

  private void generateService(String entityName, String entityDir) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String serviceCode = renderer.renderServiceTemplate(entityName, basePackage);
    Files.writeString(Path.of(entityDir + "/" + entityName + "Service.java"), serviceCode);
  }

  private void generateServiceImpl(String entityName, String entityDir) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String serviceImplCode = renderer.renderServiceImplTemplate(entityName, basePackage);
    Files.writeString(Path.of(entityDir + "/" + entityName + "ServiceImpl.java"), serviceImplCode);
  }

}
