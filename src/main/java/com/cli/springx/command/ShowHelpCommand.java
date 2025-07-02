package com.cli.springx.command;

import com.cli.springx.SpringXCli;
import com.cli.springx.service.InputOutput;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "--help", description = "Shows help for a command")
public class ShowHelpCommand implements Runnable {

  private static InputOutput inputOutput;

  public ShowHelpCommand() {
    // Constructeur vide requis par Picocli
  }

  public ShowHelpCommand(InputOutput inputOutput) {
    ShowHelpCommand.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    if (inputOutput == null) {
      System.out.println("Erreur : inputOutput non initialisé.");
      return;
    }
    commandLineUsage();
  }

  private void commandLineUsage() {
    CommandLine cmd = new CommandLine(new SpringXCli());
    for (CommandLine sub : cmd.getSubcommands().values()) {
      Object command = sub.getCommand();
      if (command instanceof ShowHelpCommand) {
        continue;
      }
      String name = sub.getCommandName();
      String description = "";
      Command annotation = command.getClass().getAnnotation(Command.class);
      if (annotation != null && annotation.description().length > 0) {
        description = annotation.description()[0];
      }
      inputOutput.print("\u001B[33m" + name + "\u001B[0m : \u001B[32m" + description + "\u001B[0m");
    }
  }
}
