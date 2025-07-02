package com.cli.springx.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FsUtils {

  /**
   * Crée le dossier s'il n'existe pas.
   * 
   * @param dirPath chemin du dossier
   * @throws IOException si erreur création
   */
  public static void createDirIfNotExists(String dirPath) throws IOException {
    Path path = Paths.get(dirPath);
    if (!Files.exists(path)) {
      Files.createDirectories(path);
      System.out.println("Création du dossier : " + dirPath);
    }
  }

  /**
   * Renvoie le chemin complet du dossier source Java en fonction du package,
   * en créant les dossiers si nécessaire.
   *
   * @param baseSrcDir  Exemple: "src/main/java"
   * @param basePackage Exemple: "com.yourname.demo"
   * @param subPackage  Exemple: "entity" ou "service.impl"
   * @return chemin complet des dossiers à créer
   * @throws IOException si erreur création
   */
  public static String preparePackagePath(String baseSrcDir, String basePackage, String subPackage) throws IOException {
    String packagePath = basePackage.replace('.', File.separatorChar);
    if (subPackage != null && !subPackage.isEmpty()) {
      packagePath += File.separator + subPackage.replace('.', File.separatorChar);
    }
    String fullPath = baseSrcDir + File.separator + packagePath;
    createDirIfNotExists(fullPath);
    return fullPath;
  }
}
