package com.example.calculator.process;

import javaslang.control.Either;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * I display the latest stack content and error if there is any.
 */
public class ResultViewer {

  private final StackHistory stackHistory;

  public ResultViewer(final StackHistory stackHistory) {
    this.stackHistory = stackHistory;
  }

  public void printResult(Either<Throwable, LinkedList<BigDecimal>> result) {
    if (result.isRight()) {
      System.out.println(stackHistory.getStackContent(result.get()));
    } else {
      System.out.println(result.getLeft().getMessage());
      System.out.println(stackHistory.getStackContent(stackHistory.getLatest()));
    }
  }

}
