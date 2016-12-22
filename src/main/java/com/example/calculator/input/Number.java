package com.example.calculator.input;

import com.example.calculator.process.InputProcessor;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Number implements ParsedInput {

  private final BigDecimal number;

  public Number(BigDecimal number) {
    this.number = number;
  }

  @Override
  public LinkedList<BigDecimal> accept(final InputProcessor processor) {
    return processor.process(this);
  }

  public BigDecimal getNumber() {
    return number;
  }
}
