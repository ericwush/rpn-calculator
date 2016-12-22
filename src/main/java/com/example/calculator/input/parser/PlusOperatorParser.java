package com.example.calculator.input.parser;

import com.example.calculator.input.PlusOperator;

import java.util.Optional;

public class PlusOperatorParser implements InputParser<PlusOperator> {
  @Override
  public Optional<PlusOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("+"))
        .map(i -> new PlusOperator());
  }
}
