package com.cli.springx.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.cli.springx.model.Attributs;
import com.cli.springx.model.Entity;
import com.cli.springx.service.InputOutput;
import com.cli.springx.util.FsUtils;
import com.cli.springx.util.LombokDetector;
import com.cli.springx.util.TemplateRenderer;

import picocli.CommandLine.Command;

@Command(name = "generate:entity", description = "Generates a new entity")
public class GenerateEntityCommand implements Runnable {

  private InputOutput inputOutput;
  private String baseSrcDir = "src/main/java";
  private String basePackage = "com.cli.springx";

  public GenerateEntityCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    commandLineUsage();
  }

  public void setInputOutput(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  private void generateEntity(Entity entity, boolean lombokPresent, String entityDir) throws IOException {
    TemplateRenderer renderer = new TemplateRenderer();
    String entityCode = renderer.renderEntityTemplate(entity, lombokPresent, basePackage);
    Files.writeString(Path.of(entityDir + "/" + entity.getName() + ".java"), entityCode);
  }

  private void commandLineUsage() {
    inputOutput.print("\u001B[32m> Entity name :\u001B[0m");
    String entityName = inputOutput.readInput().trim();
    if (entityName.isEmpty()) {
      inputOutput.printError("\u001B[31mEntity name can't be empty.\u001B[0m");
      return;
    }

    Entity entity = new Entity(entityName);
    // Ajout d'un id par défaut
    entity.addAttributs(new Attributs("id", "Long"));

    // Lecture attributs
    while (true) {
      inputOutput.print("\u001B[32m> Name attribute, or press Enter to finish :\u001B[0m");
      String input = inputOutput.readInput().trim();
      if (input.isEmpty()) {
        break;
      }

      String type = "";
      while (true) {
        inputOutput.print(
            "\u001B[32m> Type attribute for \u001B[0m" + "\u001B[33m" + input + "\u001B[0m"
                + "\u001B[32m see all type enter(\u001B[33m?\u001B[32m):\u001B[0m");
        type = inputOutput.readInput().trim();
        if (type.equalsIgnoreCase("?")) {
          inputOutput.print("\u001B[32mAvailable Types:\u001B[0m");
          inputOutput.print("\u001B[32m- String\u001B[0m");
          inputOutput.print("\u001B[32m- Integer\u001B[0m");
          inputOutput.print("\u001B[32m- Long\u001B[0m");
          inputOutput.print("\u001B[32m- Double\u001B[0m");
          inputOutput.print("\u001B[32m- Boolean\u001B[0m");
          inputOutput.print("\u001B[32m- Date\u001B[0m");
          inputOutput.print("\u001B[32m- LocalDate\u001B[0m");
          inputOutput.print("\u001B[32m- LocalDateTime\u001B[0m");
        } else {
          break;
        }
      }

      if (type.isEmpty()) {
        break;
      }
      entity.addAttributs(new Attributs(input, type));
    }

    // Lecture relations (optionnel)
    // String addRelations = inputOutput.print("Ajouter des relations ? (y/n)
    // :").trim();
    // if (addRelations.equalsIgnoreCase("y")) {
    // while (true) {
    // String relType = inputOutput.ask("Type de relation (OneToMany, ManyToOne,
    // ManyToMany), vide pour terminer :").trim();
    // if (relType.isEmpty()) break;
    //
    // String fieldName = inputOutput.ask("Nom du champ :").trim();
    // String targetEntity = inputOutput.ask("Entité cible :").trim();
    //
    // entity.addRelation(new Relation(relType, fieldName, targetEntity));
    // }
    // }

    // Détection lombok
    Path projectRoot = Paths.get("").toAbsolutePath();
    boolean lombokPresent = LombokDetector.isLombokPresent(projectRoot);
    if (lombokPresent) {
      inputOutput.print("✅\u001B[32m Lombok detected!\u001B[0m");
    } else {
      inputOutput.printError("❌\u001B[33m Lombok not detected!\u001B[0m");
    }

    // Création dossier model si absent
    try {
      String entityDir = FsUtils.preparePackagePath(baseSrcDir, basePackage, "model");
      // Génération code
      generateEntity(entity, lombokPresent, entityDir);
      inputOutput.print("🎉\u001B[32m Entity " + entityName + " generated successfully!\u001B[0m🎉");
    } catch (IOException e) {
      inputOutput.printError("\u001B[31mError while generating entity: " + e.getMessage() + "\u001B[0m");
    }
  }

}
