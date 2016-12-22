package com.example.calculator.process

import com.example.calculator.input.parser.CommandLineParser
import javaslang.control.Either
import javaslang.control.Try
import spock.lang.Specification

class CommandLineExecutorSpec extends Specification {

  CommandLineExecutor commandLineExecutor
  CommandLineParser parser
  CommandLineProcessor processor
  ResultViewer viewer

  def setup() {
    parser = Mock()
    processor = Stub()
    viewer = Mock()
    commandLineExecutor = new CommandLineExecutor(parser, processor, viewer)
  }

  def cleanup() {
  }

  def "test command line executor"() {
    when:
    def input = "1 2"
    Observable observable = Mock()
    processor.process(_ as List) >> Try.success(new LinkedList(Arrays.asList(new BigDecimal(1))))
    commandLineExecutor.update(observable, input)

    then:
    1 * parser.parse("1")
    1 * parser.parse("2")
    1 * viewer.printResult(Either.right(new LinkedList(Arrays.asList(new BigDecimal(1)))))
  }

  def "test empty input"() {
    RuntimeException exception = Mock()
    when:
    def inputString = input
    Observable observable = Mock()
    processor.process(_ as List) >> Try.failure(exception)
    commandLineExecutor.update(observable, inputString)

    then:
    1 * parser.parse("")
    1 * viewer.printResult(Either.left(exception))

    where:
    input << ["", " "]
  }

}
