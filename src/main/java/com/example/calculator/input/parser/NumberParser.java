package com.example.calculator.input.parser;

import com.example.calculator.input.Number;
import javaslang.control.Try;

import java.math.BigDecimal;
import java.util.Optional;

public class NumberParser implements InputParser<Number> {
  @Override
  public Optional<Number> parse(final String input) {
    return Try.of(() -> new Number(new BigDecimal(input))).toEither().toJavaOptional();
  }
}
