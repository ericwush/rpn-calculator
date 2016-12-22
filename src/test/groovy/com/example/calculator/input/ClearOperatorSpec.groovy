package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class ClearOperatorSpec extends Specification {

  ClearOperator clearOperator
  InputProcessor processor

  def setup() {
    clearOperator = new ClearOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    when:
    processor = Mock()
    processor.process(clearOperator) >> returnValue
    def result = clearOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    clearOperator.setPosition(position)

    then:
    clearOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    clearOperator.getName() == "clear"
  }

}
