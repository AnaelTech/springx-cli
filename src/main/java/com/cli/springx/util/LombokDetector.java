package com.cli.springx.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LombokDetector {

  /**
   * Vérifie si le pom.xml contient une dépendance lombok.
   * 
   * @param projectRootPath Chemin racine du projet Spring Boot.
   * @return true si lombok est présent, false sinon.
   */
  public static boolean isLombokPresent(Path projectRootPath) {
    Path pomPath = projectRootPath.resolve("pom.xml");
    if (!Files.exists(pomPath)) {
      System.out.println("pom.xml non trouvé à " + pomPath.toString());
      return false;
    }

    try {
      String pomContent = Files.readString(pomPath);
      return pomContent.contains("<artifactId>lombok</artifactId>");
    } catch (IOException e) {
      System.err.println("Erreur lecture pom.xml: " + e.getMessage());
      return false;
    }
  }
}
