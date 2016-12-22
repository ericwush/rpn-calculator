package com.example.calculator.input.parser;

import com.example.calculator.input.SubtractionOperator;

import java.util.Optional;

public class SubtractionOperatorParser implements InputParser<SubtractionOperator> {
  @Override
  public Optional<SubtractionOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("-"))
        .map(i -> new SubtractionOperator());
  }
}
