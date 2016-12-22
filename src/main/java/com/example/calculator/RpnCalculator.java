package com.example.calculator;

import com.example.calculator.input.parser.SquareRootOperatorParser;
import com.example.calculator.input.parser.AdditionOperatorParser;
import com.example.calculator.input.parser.ClearOperatorParser;
import com.example.calculator.input.parser.CommandLineParser;
import com.example.calculator.input.parser.DivisionOperatorParser;
import com.example.calculator.input.parser.MultiplicationOperatorParser;
import com.example.calculator.input.parser.NumberParser;
import com.example.calculator.input.parser.SubtractionOperatorParser;
import com.example.calculator.input.parser.UndoOperatorParser;
import com.example.calculator.process.CommandLineExecutor;
import com.example.calculator.process.CommandLineListener;
import com.example.calculator.process.CommandLineProcessor;
import com.example.calculator.process.InputProcessor;
import com.example.calculator.process.ResultViewer;
import com.example.calculator.process.StackHistory;

public class RpnCalculator {

  public static void main(String[] args) {
    StackHistory stackHistory = new StackHistory();
    CommandLineParser parser = new CommandLineParser(
        new NumberParser(),
        new AdditionOperatorParser(),
        new SubtractionOperatorParser(),
        new MultiplicationOperatorParser(),
        new DivisionOperatorParser(),
        new SquareRootOperatorParser(),
        new UndoOperatorParser(),
        new ClearOperatorParser());
    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(new InputProcessor(stackHistory));
    CommandLineListener commandLineListener = new CommandLineListener();
    commandLineListener.addObserver(new CommandLineExecutor(parser, commandLineProcessor, new ResultViewer(stackHistory)));
    new Thread(commandLineListener).start();
  }

}
