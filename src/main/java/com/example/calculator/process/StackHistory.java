package com.example.calculator.process;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class StackHistory {

  private LinkedList<LinkedList<BigDecimal>> stacks = new LinkedList<>();

  public StackHistory() {
    stacks = new LinkedList<>();
  }

  public LinkedList<BigDecimal> getLatest() {
    return Optional.ofNullable(stacks.peekFirst()).map(stack -> {
      LinkedList<BigDecimal> newStack = new LinkedList<>();
      stack.forEach(newStack::add);
      return newStack;
    }).orElse(new LinkedList<>());
  }

  public void add(LinkedList<BigDecimal> stack) {
    stacks.push(stack);
  }

  public LinkedList<BigDecimal> popAndGetLatest() {
    stacks.poll();
    return this.getLatest();
  }

  public String getStackContent(LinkedList<BigDecimal> stack) {
    Collections.reverse(stack);
    return "stack: " +
        stack.stream()
            .map(d -> d.setScale(10, BigDecimal.ROUND_HALF_UP))
            .map(BigDecimal::stripTrailingZeros)
            .map(BigDecimal::toPlainString)
            .collect(joining(" "));
  }

}
