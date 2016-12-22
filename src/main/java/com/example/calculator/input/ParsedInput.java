package com.example.calculator.input;

import com.example.calculator.process.InputProcessor;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface ParsedInput {
  LinkedList<BigDecimal> accept(InputProcessor processor);
}
