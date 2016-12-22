package com.example.calculator.process;

import com.example.calculator.input.ParsedInput;
import com.example.calculator.input.parser.CommandLineParser;
import javaslang.control.Try;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CommandLineExecutor implements Observer {

  private final CommandLineParser parser;
  private final CommandLineProcessor processor;

  public CommandLineExecutor(CommandLineParser parser, CommandLineProcessor processor) {
    this.parser = parser;
    this.processor = processor;
  }

  @Override
  public void update(final Observable listener, final Object commandLineString) {
    String[] inputs = ((String) commandLineString).split(" ");
    List<Optional<ParsedInput>> parsedInputs = Arrays.stream(inputs).map(parser::parse).collect(toList());
    Try<LinkedList<BigDecimal>> processResult = processor.process(parsedInputs);
    System.out.println(processResult.get().stream().map(BigDecimal::toString).collect(joining(" ")));
  }
}