package com.cli.springx.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PackageDetector {

  /**
   * Détecte automatiquement le package principal du projet Spring Boot
   * en cherchant la classe avec @SpringBootApplication
   */
  public static Optional<String> detectMainPackage() {
    try {
      Path srcDir = Paths.get("src/main/java");
      if (!Files.exists(srcDir)) {
        return Optional.empty();
      }

      // Recherche récursive de @SpringBootApplication
      try (Stream<Path> paths = Files.walk(srcDir)) {
        return paths
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .map(PackageDetector::extractPackageFromFile)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
      }
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  /**
   * Extrait le package d'un fichier Java s'il contient @SpringBootApplication
   */
  private static Optional<String> extractPackageFromFile(Path javaFile) {
    try {
      List<String> lines = Files.readAllLines(javaFile);

      // Vérifie si le fichier contient @SpringBootApplication
      boolean hasSpringBootApp = lines.stream()
          .anyMatch(line -> line.trim().contains("@SpringBootApplication"));

      if (!hasSpringBootApp) {
        return Optional.empty();
      }

      // Extrait le package
      return lines.stream()
          .filter(line -> line.trim().startsWith("package "))
          .map(line -> line.trim())
          .map(line -> line.substring(8, line.length() - 1)) // Enlève "package " et ";"
          .findFirst();

    } catch (IOException e) {
      return Optional.empty();
    }
  }

  /**
   * Détecte le package à partir du pom.xml (groupId)
   */
  public static Optional<String> detectPackageFromPom() {
    try {
      Path pomFile = Paths.get("pom.xml");
      if (!Files.exists(pomFile)) {
        return Optional.empty();
      }
      String groupId = null;
      boolean beforeParent = true;
      for (String line : Files.readAllLines(pomFile)) {
        String trimmed = line.trim();
        if (trimmed.startsWith("<parent>")) {
          beforeParent = false;
        }
        if (beforeParent && trimmed.startsWith("<groupId>") && groupId == null) {
          groupId = trimmed.replaceAll("</?groupId>", "").trim();
        }
      }
      // Si pas trouvé avant <parent>, cherche après (cas rare)
      if (groupId == null) {
        for (String line : Files.readAllLines(pomFile)) {
          String trimmed = line.trim();
          if (trimmed.startsWith("<groupId>")) {
            groupId = trimmed.replaceAll("</?groupId>", "").trim();
            break;
          }
        }
      }
      return Optional.ofNullable(groupId);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  /**
   * Stratégie complète de détection
   */
  public static String detectBasePackage() {
    // 1. Essaie de détecter via @SpringBootApplication
    Optional<String> mainPackage = detectMainPackage();
    if (mainPackage.isPresent()) {
      return mainPackage.get();
    }

    // 2. Essaie via pom.xml
    Optional<String> pomPackage = detectPackageFromPom();
    if (pomPackage.isPresent()) {
      return pomPackage.get();
    }

    // 3. Fallback par défaut
    return "com.example.demo";
  }
}
