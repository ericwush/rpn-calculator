package com.example.calculator.input.parser;

import com.example.calculator.input.MinusOperator;

import java.util.Optional;

public class MinusOperatorParser implements InputParser<MinusOperator> {
  @Override
  public Optional<MinusOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("-"))
        .map(i -> new MinusOperator());
  }
}
