package com.cli.springx.command;

import com.cli.springx.service.InputOutput;

import picocli.CommandLine.Command;

@Command(name = "--version", description = "Shows the version of the CLI")
public class ShowVersionCommand implements Runnable {

  private InputOutput inputOutput;

  public ShowVersionCommand() {

  }

  public ShowVersionCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    ShowVersion();
  }

  public void ShowVersion() {
    inputOutput.print("SpringX CLI version 1.0.0");
  }

}
