package com.example.calculator.input;

import com.example.calculator.process.InputProcessor;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface ParsedInput extends PositionedInput, NamedInput {

  LinkedList<BigDecimal> accept(InputProcessor processor);

}
