package com.example.calculator.process;

import com.example.calculator.input.ClearOperator;
import com.example.calculator.input.MinusOperator;
import com.example.calculator.input.Number;
import com.example.calculator.input.PlusOperator;
import com.example.calculator.input.UndoOperator;

public interface Processor<T> {
  T process(Number number);
  T process(PlusOperator plusOperator);
  T process(MinusOperator minusOperator);
  T process(UndoOperator undoOperator);
  T process(ClearOperator clearOperator);
}
