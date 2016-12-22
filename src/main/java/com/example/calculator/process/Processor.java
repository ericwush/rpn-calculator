package com.example.calculator.process;

import com.example.calculator.input.ClearOperator;
import com.example.calculator.input.DivisionOperator;
import com.example.calculator.input.MultiplicationOperator;
import com.example.calculator.input.SquareRootOperator;
import com.example.calculator.input.SubtractionOperator;
import com.example.calculator.input.Number;
import com.example.calculator.input.AdditionOperator;
import com.example.calculator.input.UndoOperator;

public interface Processor<T> {

  T process(Number number);

  T process(AdditionOperator additionOperator);

  T process(SubtractionOperator subtractionOperator);

  T process(MultiplicationOperator multiplicationOperator);

  T process(DivisionOperator divisionOperator);

  T process(SquareRootOperator squareRootOperator);

  T process(UndoOperator undoOperator);

  T process(ClearOperator clearOperator);

}

