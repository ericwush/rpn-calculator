package com.example.calculator.process;

import com.example.calculator.input.ParsedInput;
import javaslang.Tuple2;
import javaslang.control.Try;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CommandLineProcessor {

  private InputProcessor processor;

  public CommandLineProcessor(final InputProcessor processor) {
    this.processor = processor;
  }

  public Try<LinkedList<BigDecimal>> process(final List<Optional<ParsedInput>> parsedInputs) {
    return Try.of(() ->
        javaslang.collection.List.ofAll(parsedInputs)
            .zipWithIndex()
            .toJavaStream()
            .map(processInput())
            .reduce((first, second) -> second)
            .orElseThrow(() -> new IllegalStateException("Unexpected empty stack"))
    );
  }

  private Function<Tuple2<Optional<ParsedInput>, Long>, LinkedList<BigDecimal>> processInput() {
    return maybeParsedInput -> {
      ParsedInput parsedInput = maybeParsedInput._1
          .orElseThrow(
              () -> new IllegalArgumentException("position " + (maybeParsedInput._2.intValue() + 1) + ": invalid input"));
      parsedInput.setPosition(maybeParsedInput._2.intValue() + 1);
      return parsedInput.accept(processor);
    };
  }
}
