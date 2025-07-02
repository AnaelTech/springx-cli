package com.cli.springx;

import com.cli.springx.command.GenerateControllerCommand;
import com.cli.springx.command.GenerateDtoCommand;
import com.cli.springx.command.GenerateEntityCommand;
import com.cli.springx.command.GenerateRepositoryCommand;
import com.cli.springx.command.GenerateServiceCommand;
import com.cli.springx.command.ShowHelpCommand;
import com.cli.springx.command.ShowVersionCommand;
import com.cli.springx.service.InputOutput;
import com.cli.springx.service.impl.InputOutputImpl;

import picocli.CommandLine;

@CommandLine.Command(name = "springx", mixinStandardHelpOptions = true, version = "1.0", subcommands = {
    ShowVersionCommand.class,
    ShowHelpCommand.class,
    GenerateEntityCommand.class,
    GenerateControllerCommand.class,
    GenerateServiceCommand.class,
    GenerateRepositoryCommand.class,
    GenerateDtoCommand.class,
}, description = "SpringX CLI - a command line tool for SpringX")

public class SpringXCli implements Runnable {
  private InputOutput io = new InputOutputImpl();

  public static void main(String[] args) {
    int exitCode = new CommandLine(new SpringXCli()).execute(args);

    System.exit(exitCode);
  }

  @Override
  public void run() {
    System.out.println("Welcome to SpringX CLI! - use --help to see available commands");
    // GenerateEntityCommand generateEntityCommand = new GenerateEntityCommand(io);
    // generateEntityCommand.run();
    // GenerateControllerCommand generateControllerCommand = new
    // GenerateControllerCommand(io);
    // generateControllerCommand.run();
    // GenerateRepositoryCommand generateRepositoryCommand = new
    // GenerateRepositoryCommand(io);
    // generateRepositoryCommand.run();
    // GenerateServiceCommand generateServiceCommand = new
    // GenerateServiceCommand(io);
    // generateServiceCommand.run();
    ShowHelpCommand showHelpCommand = new ShowHelpCommand(io);
    showHelpCommand.run();
  }

}
