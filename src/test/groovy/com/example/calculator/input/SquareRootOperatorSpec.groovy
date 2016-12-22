package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class SquareRootOperatorSpec extends Specification {

  SquareRootOperator squareRootOperator

  def setup() {
    squareRootOperator = new SquareRootOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    InputProcessor processor = Mock()
    when:
    processor.process(squareRootOperator) >> returnValue
    def result = squareRootOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    squareRootOperator.setPosition(position)

    then:
    squareRootOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    squareRootOperator.getName() == "sqrt"
  }

}
