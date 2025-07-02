package com.cli.springx.command;

import picocli.CommandLine.Command;

@Command(name = "--version", description = "Shows the version of the CLI")
public class ShowVersionCommand implements Runnable {

  @Override
  public void run() {

  }

}
