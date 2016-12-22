package com.example.calculator.process;

import com.example.calculator.input.ClearOperator;
import com.example.calculator.input.MinusOperator;
import com.example.calculator.input.Number;
import com.example.calculator.input.PlusOperator;
import com.example.calculator.input.UndoOperator;

import java.math.BigDecimal;
import java.util.LinkedList;

public class InputProcessor implements Processor<LinkedList<BigDecimal>> {
  private final StackHistory stackHistory;

  public InputProcessor(StackHistory stackHistory) {
    this.stackHistory = stackHistory;
  }

  @Override
  public LinkedList<BigDecimal> process(final Number number) {
    LinkedList<BigDecimal> stack = stackHistory.getLatest();
    stack.push(number.getNumber());
    stackHistory.add(stack);
    return stack;
  }

  @Override
  public LinkedList<BigDecimal> process(final PlusOperator plusOperator) {
    return operate(Operation.ADDITION);
  }

  @Override
  public LinkedList<BigDecimal> process(final MinusOperator minusOperator) {
    return operate(Operation.SUBTRACTION);
  }

  @Override
  public LinkedList<BigDecimal> process(final UndoOperator undoOperator) {
    return stackHistory.popAndGetLatest();
  }

  @Override
  public LinkedList<BigDecimal> process(final ClearOperator clearOperator) {
    LinkedList<BigDecimal> clearStack = new LinkedList<>();
    stackHistory.add(clearStack);
    return clearStack;
  }

  enum Operation {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
  }

  private LinkedList<BigDecimal> operate(Operation operation) {
    LinkedList<BigDecimal> stack = stackHistory.getLatest();
    if (stack.size() < 2) {
      throw new IllegalStateException("insufficient parameters");
    }
    switch (operation) {
      case ADDITION:
        stack.push(stack.pop().add(stack.pop()));
        break;
      case SUBTRACTION:
        stack.push(stack.pop().subtract(stack.pop()));
        break;
      case MULTIPLICATION:
      case DIVISION:
    }

    stackHistory.add(stack);
    return stack;
  }
}
