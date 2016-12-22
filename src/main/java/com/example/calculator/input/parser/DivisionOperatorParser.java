package com.example.calculator.input.parser;

import com.example.calculator.input.DivisionOperator;

import java.util.Optional;

public class DivisionOperatorParser implements InputParser<DivisionOperator> {
  @Override
  public Optional<DivisionOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("/"))
        .map(i -> new DivisionOperator());
  }
}
