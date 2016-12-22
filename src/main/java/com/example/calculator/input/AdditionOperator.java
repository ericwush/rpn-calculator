package com.example.calculator.input;

import com.example.calculator.process.InputProcessor;

import java.math.BigDecimal;
import java.util.LinkedList;

public class AdditionOperator implements ParsedInput {
  private int position;

  @Override
  public LinkedList<BigDecimal> accept(final InputProcessor processor) {
    return processor.process(this);
  }

  @Override
  public void setPosition(final int position) {
    this.position = position;
  }

  @Override
  public int getPosition() {
    return position;
  }

  @Override
  public String getName() {
    return "+";
  }
}
