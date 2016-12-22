package com.example.calculator;

import com.example.calculator.process.CommandLineExecutor;
import com.example.calculator.process.CommandLineListener;
import com.example.calculator.input.parser.ClearOperatorParser;
import com.example.calculator.input.parser.CommandLineParser;
import com.example.calculator.input.parser.MinusOperatorParser;
import com.example.calculator.input.parser.NumberParser;
import com.example.calculator.input.parser.PlusOperatorParser;
import com.example.calculator.input.parser.UndoOperatorParser;
import com.example.calculator.process.CommandLineProcessor;
import com.example.calculator.process.InputProcessor;
import com.example.calculator.process.StackHistory;

public class RpnCalculator {

  public static void main(String[] args) {
    CommandLineParser parser = new CommandLineParser(
        new NumberParser(),
        new PlusOperatorParser(),
        new MinusOperatorParser(),
        new UndoOperatorParser(),
        new ClearOperatorParser());
    CommandLineProcessor commandLineProcessor = new CommandLineProcessor(new InputProcessor(StackHistory.getInstance()));
    CommandLineListener commandLineListener = new CommandLineListener();
    commandLineListener.addObserver(new CommandLineExecutor(parser, commandLineProcessor));
    new Thread(commandLineListener).start();
  }

}
