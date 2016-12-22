package com.example.calculator.process;

import javaslang.control.Either;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class ResultViewer {

  public void printResult(Either<Throwable, LinkedList<BigDecimal>> result) {
    if (result.isRight()) {
      printStack(result.get());
    } else {
      printErrorAndStack(result.getLeft());
    }
  }

  private void printStack(LinkedList<BigDecimal> stack) {
    Collections.reverse(stack);
    System.out.println("stack: " +
        stack.stream()
            .map(d -> d.setScale(10, BigDecimal.ROUND_HALF_UP))
            .map(BigDecimal::stripTrailingZeros)
            .map(BigDecimal::toPlainString)
            .collect(joining(" ")));
  }

  private void printErrorAndStack(Throwable e) {
    System.out.println(e.getMessage());
    this.printStack(StackHistory.getInstance().getLatest());
  }
}
