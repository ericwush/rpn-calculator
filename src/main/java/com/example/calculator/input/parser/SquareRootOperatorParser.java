package com.example.calculator.input.parser;

import com.example.calculator.input.SquareRootOperator;

import java.util.Optional;

public class SquareRootOperatorParser implements InputParser<SquareRootOperator> {
  @Override
  public Optional<SquareRootOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equalsIgnoreCase("sqrt"))
        .map(i -> new SquareRootOperator());
  }
}
