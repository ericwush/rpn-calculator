package com.example.calculator.input.parser;

import com.example.calculator.input.UndoOperator;

import java.util.Optional;

public class UndoOperatorParser implements InputParser<UndoOperator> {
  @Override
  public Optional<UndoOperator> parse(final String input) {
    return Optional.ofNullable(input)
        .filter(i -> i.equalsIgnoreCase("undo"))
        .map(i -> new UndoOperator());
  }
}
