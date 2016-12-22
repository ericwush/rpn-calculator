package com.example.calculator.input.parser;

import com.example.calculator.input.MultiplicationOperator;

import java.util.Optional;

public class MultiplicationOperatorParser implements InputParser<MultiplicationOperator> {
  @Override
  public Optional<MultiplicationOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equals("*"))
        .map(i -> new MultiplicationOperator());
  }
}
