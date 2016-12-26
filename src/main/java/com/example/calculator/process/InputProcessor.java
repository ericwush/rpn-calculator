package com.example.calculator.process;

import com.example.calculator.input.AdditionOperator;
import com.example.calculator.input.ClearOperator;
import com.example.calculator.input.DivisionOperator;
import com.example.calculator.input.MultiplicationOperator;
import com.example.calculator.input.Number;
import com.example.calculator.input.ParsedInput;
import com.example.calculator.input.SquareRootOperator;
import com.example.calculator.input.SubtractionOperator;
import com.example.calculator.input.UndoOperator;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * I am responsible for processing all types of valid parsed input.
 */
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
    return stackHistory.getLatest();
  }

  @Override
  public LinkedList<BigDecimal> process(final AdditionOperator additionOperator) {
    return operate(additionOperator, Operation.ADDITION);
  }

  @Override
  public LinkedList<BigDecimal> process(final SubtractionOperator subtractionOperator) {
    return operate(subtractionOperator, Operation.SUBTRACTION);
  }

  @Override
  public LinkedList<BigDecimal> process(final MultiplicationOperator multiplicationOperator) {
    return operate(multiplicationOperator, Operation.MULTIPLICATION);
  }

  @Override
  public LinkedList<BigDecimal> process(final DivisionOperator divisionOperator) {
    return operate(divisionOperator, Operation.DIVISION);
  }

  @Override
  public LinkedList<BigDecimal> process(final SquareRootOperator squareRootOperator) {
    LinkedList<BigDecimal> stack = stackHistory.getLatest();
    if (stack.size() < 1) {
      throw new IllegalStateException(
          "operator <" + squareRootOperator.getName()
              + "> (position: " + squareRootOperator.getPosition() + "): insufficient parameters");
    }
    stack.push(new BigDecimal(Math.sqrt(stack.pop().doubleValue())));
    stackHistory.add(stack);
    return stackHistory.getLatest();
  }

  @Override
  public LinkedList<BigDecimal> process(final UndoOperator undoOperator) {
    return stackHistory.popAndGetLatest();
  }

  @Override
  public LinkedList<BigDecimal> process(final ClearOperator clearOperator) {
    LinkedList<BigDecimal> clearStack = new LinkedList<>();
    stackHistory.add(clearStack);
    return stackHistory.getLatest();
  }

  enum Operation {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
  }

  private LinkedList<BigDecimal> operate(ParsedInput operator, Operation operation) {
    LinkedList<BigDecimal> stack = stackHistory.getLatest();
    if (stack.size() < 2) {
      throw new IllegalStateException(
          "operator <" + operator.getName() + "> (position: " + operator.getPosition() + "): insufficient parameters");
    }

    switch (operation) {
      case ADDITION:
        stack.push(stack.pop().add(stack.pop()));
        break;
      case SUBTRACTION:
        BigDecimal subtractor = stack.pop();
        BigDecimal subtrahend = stack.pop();
        stack.push(subtrahend.subtract(subtractor));
        break;
      case MULTIPLICATION:
        stack.push(stack.pop().multiply(stack.pop()));
        break;
      case DIVISION:
        BigDecimal divisor = stack.pop();
        BigDecimal dividend = stack.pop();
        stack.push(dividend.divide(divisor, 20, BigDecimal.ROUND_HALF_UP));
        break;
    }

    stackHistory.add(stack);
    return stackHistory.getLatest();
  }

}
