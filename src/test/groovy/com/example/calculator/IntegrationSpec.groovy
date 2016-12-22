package com.example.calculator

import com.example.calculator.input.parser.AdditionOperatorParser
import com.example.calculator.input.parser.ClearOperatorParser
import com.example.calculator.input.parser.CommandLineParser
import com.example.calculator.input.parser.DivisionOperatorParser
import com.example.calculator.input.parser.MultiplicationOperatorParser
import com.example.calculator.input.parser.NumberParser
import com.example.calculator.input.parser.SquareRootOperatorParser
import com.example.calculator.input.parser.SubtractionOperatorParser
import com.example.calculator.input.parser.UndoOperatorParser
import com.example.calculator.process.CommandLineExecutor
import com.example.calculator.process.CommandLineProcessor
import com.example.calculator.process.InputProcessor
import com.example.calculator.process.ResultViewer
import com.example.calculator.process.StackHistory
import spock.lang.Specification

class IntegrationSpec extends Specification {

  CommandLineExecutor commandLineExecutor
  CommandLineParser parser
  CommandLineProcessor processor
  ResultViewer viewer
  StackHistory stackHistory

  def setup() {
    stackHistory = new StackHistory()
    parser = new CommandLineParser(
        new NumberParser(),
        new AdditionOperatorParser(),
        new SubtractionOperatorParser(),
        new MultiplicationOperatorParser(),
        new DivisionOperatorParser(),
        new SquareRootOperatorParser(),
        new UndoOperatorParser(),
        new ClearOperatorParser())
    processor = new CommandLineProcessor(new InputProcessor(stackHistory))
    viewer = Mock()
    commandLineExecutor = new CommandLineExecutor(parser, processor, viewer)
  }

  def cleanup() {
  }

  def "test"() {
    when:
    Observable observable = Mock()
    commandLineExecutor.update(observable, input)
    def result = stackHistory.getStackContent(stackHistory.getLatest())

    then:
    result == content

    where:
    input << ["1 2 +", "3 1 -", "4 5 *", "9 3 /", "9 sqrt", "clear"]
    content << ["stack: 3", "stack: 2", "stack: 20", "stack: 3", "stack: 3", "stack: "]
  }

}
