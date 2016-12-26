package com.example.calculator.process;

import com.example.calculator.input.ParsedInput;
import com.example.calculator.input.parser.CommandLineParser;
import javaslang.control.Either;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * I receive the command line inputs which will be parsed and processed and I also display the result.
 */
public class CommandLineExecutor implements Observer {

  private final CommandLineParser parser;
  private final CommandLineProcessor processor;
  private final ResultViewer viewer;

  public CommandLineExecutor(CommandLineParser parser, CommandLineProcessor processor, ResultViewer viewer) {
    this.parser = parser;
    this.processor = processor;
    this.viewer = viewer;
  }

  @Override
  public void update(final Observable listener, final Object commandLineString) {
    String[] inputs = ((String) commandLineString).trim().split(" ");
    List<Optional<ParsedInput>> parsedInputs = Arrays.stream(inputs).map(parser::parse).collect(toList());
    Either<Throwable, LinkedList<BigDecimal>> processResult = processor.process(parsedInputs).toEither();
    viewer.printResult(processResult);
  }

}