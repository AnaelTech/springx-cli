package com.cli.springx.util;

import com.cli.springx.model.Entity;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TemplateRenderer {

  private Handlebars handlebars = new Handlebars();

  public String renderEntityTemplate(Entity entity, boolean lombok, String basePackage) throws IOException {
    String templateStr = Files.readString(Path.of("src/main/resources/templates/entity.tpl"));
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
    String templateStr = Files.readString(Path.of("src/main/resources/templates/controller.tpl"));
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderServiceTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = Files.readString(Path.of("src/main/resources/templates/service.tpl"));
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderServiceImplTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = Files.readString(Path.of("src/main/resources/templates/serviceImpl.tpl"));
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }

  public String renderRepositoryTemplate(String entityName, String basePackage) throws IOException {
    String templateStr = Files.readString(Path.of("src/main/resources/templates/repository.tpl"));
    Template template = handlebars.compileInline(templateStr);

    Map<String, Object> context = new HashMap<>();
    context.put("package", basePackage);
    context.put("EntityName", entityName);

    return template.apply(context);
  }
}
