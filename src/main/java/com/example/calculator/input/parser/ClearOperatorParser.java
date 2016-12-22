package com.example.calculator.input.parser;

import com.example.calculator.input.ClearOperator;

import java.util.Optional;

public class ClearOperatorParser implements InputParser<ClearOperator> {

  @Override
  public Optional<ClearOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equalsIgnoreCase("clear"))
        .map(i -> new ClearOperator());
  }

}
