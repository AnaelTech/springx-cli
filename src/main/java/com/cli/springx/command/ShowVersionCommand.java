package com.cli.springx.command;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.cli.springx.SpringXCli;
import com.cli.springx.service.InputOutput;

import picocli.CommandLine.Command;
import picocli.CommandLine;

@Command(name = "--version", description = "Shows the version of the CLI")
public class ShowVersionCommand implements Runnable {

  private InputOutput inputOutput;

  @CommandLine.ParentCommand
  private SpringXCli parent;

  public ShowVersionCommand() {

  }

  public ShowVersionCommand(InputOutput inputOutput) {
    this.inputOutput = inputOutput;
  }

  @Override
  public void run() {
    ShowVersion();
  }

  private InputOutput getInputOutput() {
    return parent != null ? parent.getIo() : inputOutput;
  }

  public void ShowVersion() {
    this.inputOutput = getInputOutput();
    try {
      String content = new String(Files.readAllBytes(Paths.get(".release-please-manifest.json")));
      JSONObject json = new JSONObject(content);
      String version = json.getString(".");
      inputOutput.print("SpringX CLI version " + version);
    } catch (Exception e) {
      inputOutput.print("SpringX CLI version (inconnue)");
    }
  }

}
