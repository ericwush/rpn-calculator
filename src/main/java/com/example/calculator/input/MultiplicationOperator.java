package com.example.calculator.input;

import com.example.calculator.process.InputProcessor;

import java.math.BigDecimal;
import java.util.LinkedList;

public class MultiplicationOperator implements ParsedInput {
  @Override
  public LinkedList<BigDecimal> accept(final InputProcessor processor) {
    return processor.process(this);
  }
}
