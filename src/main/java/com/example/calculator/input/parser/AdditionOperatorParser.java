package com.example.calculator.input.parser;

import com.example.calculator.input.AdditionOperator;

import java.util.Optional;

public class AdditionOperatorParser implements InputParser<AdditionOperator> {
  @Override
  public Optional<AdditionOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("+"))
        .map(i -> new AdditionOperator());
  }
}
