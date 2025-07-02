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

@Command(name = "generate:repository", description = "Generates a new repository class")
public class GenerateRepositoryCommand implements Runnable {

  private InputOutput inputOutput;
  private String baseSrcDir = "src/main/java";
  private Optional<String> basePackage = Optional.empty();

  @CommandLine.ParentCommand
  private SpringXCli parent;

  public GenerateRepositoryCommand() {
    // Constructeur vide requis par Picocli
  }

  public GenerateRepositoryCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    this.basePackage = PackageDetector.detectPackageFromPom();
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
      String entityDir = FsUtils.preparePackagePath(baseSrcDir, basePackage, "repository");
      generateRepository(entityName, entityDir);
      inputOutput.print("🎉\u001B[32m Repository " + entityName + " generated successfully!\u001B[0m🎉");
    } catch (IOException e) {
      inputOutput.printError("❌\u001B[31mError while generating repository: " + e.getMessage() + "\u001B[0m");
    }
  }

  private void generateRepository(String entityName, String entityDir) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String repositoryCode = renderer.renderRepositoryTemplate(entityName, basePackage);
    Files.writeString(Path.of(entityDir + "/" + entityName + "Repository.java"), repositoryCode);
  }

}
