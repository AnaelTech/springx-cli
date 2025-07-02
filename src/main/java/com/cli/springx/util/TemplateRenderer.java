package com.cli.springx.util;

import com.cli.springx.model.Entity;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TemplateRenderer {

  private Handlebars handlebars = new Handlebars();

  private String readTemplateFromClasspath(String resourcePath) throws IOException {
    try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
      if (is == null) {
        throw new IOException("Template not found: " + resourcePath);
      }
      return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }
  }

  public String renderEntityTemplate(Entity entity, boolean lombok, String basePackage) throws IOException {
    String templateStr = readTemplateFromClasspath("templates/entity.tpl");
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entity.getName());
    context.put("lombok", lombok);
    context.put("attributes", entity.getAttributs());
    // context.put("relations", entity.getRelations());

    return template.apply(context);
  }

  public String renderControllerTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = readTemplateFromClasspath("templates/controller.tpl");
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderServiceTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = readTemplateFromClasspath("templates/service.tpl");
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderServiceImplTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = readTemplateFromClasspath("templates/serviceImpl.tpl");
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderRepositoryTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = readTemplateFromClasspath("templates/repository.tpl");
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }
}
