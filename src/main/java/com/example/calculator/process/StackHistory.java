package com.example.calculator.process;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;

public class StackHistory {
  private static StackHistory instance = null;
  private LinkedList<LinkedList<BigDecimal>> stacks = new LinkedList<>();

  private StackHistory() {
    stacks = new LinkedList<>();
  }

  public static synchronized StackHistory getInstance() {
    if (instance == null) {
      instance = new StackHistory();
    }
    return instance;
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
}
