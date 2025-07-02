package com.cli.springx.command;

import picocli.CommandLine.Command;

@Command(name = "--help", description = "Shows help for a command")
public class ShowHelpCommand implements Runnable {

  @Override
  public void run() {

  }

}
