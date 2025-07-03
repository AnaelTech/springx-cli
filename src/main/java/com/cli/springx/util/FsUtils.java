package com.cli.springx.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FsUtils {

  /**
   * Prépare le chemin du package avec gestion d'Optional
   */
  public static String preparePackagePath(String baseSrcDir, Optional<String> basePackage, String subPackage)
      throws IOException {
    // Utilise le package détecté ou un fallback par défaut
    String resolvedPackage = basePackage.orElse("com.example.demo");

    String packagePath = resolvedPackage.replace('.', File.separatorChar);
    if (subPackage != null && !subPackage.isEmpty()) {
      packagePath += File.separator + subPackage.replace('.', File.separatorChar);
    }
    String fullPath = baseSrcDir + File.separator + packagePath;
    createDirIfNotExists(fullPath);
    return fullPath;
  }

  /**
   * Surcharge pour maintenir la compatibilité avec String
   */
  public static String preparePackagePath(String baseSrcDir, String basePackage, String subPackage) throws IOException {
    return preparePackagePath(baseSrcDir, Optional.ofNullable(basePackage), subPackage);
  }

  /**
   * Version simplifiée qui utilise automatiquement la détection de package
   */
  public static String preparePackagePathWithDetection(String baseSrcDir, String subPackage) throws IOException {
    Optional<String> detectedPackage = PackageDetector.detectPackageFromPom();
    return preparePackagePath(baseSrcDir, detectedPackage, subPackage);
  }

  private static void createDirIfNotExists(String fullPath) throws IOException {
    Path path = Paths.get(fullPath);
    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }
  }

  public static boolean checkIfFolderExists(String folderPath, String basePackage, String folder) {
    Path path = Paths
        .get(folderPath + File.separator + basePackage.replace('.', File.separatorChar) + File.separator + folder);
    return Files.exists(path) && Files.isDirectory(path);
  }
}
