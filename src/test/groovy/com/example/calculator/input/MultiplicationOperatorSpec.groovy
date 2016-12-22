package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class MultiplicationOperatorSpec extends Specification {

  MultiplicationOperator multiplicationOperator

  def setup() {
    multiplicationOperator = new MultiplicationOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    InputProcessor processor = Mock()
    when:
    processor.process(multiplicationOperator) >> returnValue
    def result = multiplicationOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    multiplicationOperator.setPosition(position)

    then:
    multiplicationOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    multiplicationOperator.getName() == "*"
  }

}
