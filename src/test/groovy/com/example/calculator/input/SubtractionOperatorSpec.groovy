package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class SubtractionOperatorSpec extends Specification {

  SubtractionOperator subtractionOperator
  InputProcessor processor

  def setup() {
    subtractionOperator = new SubtractionOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    when:
    processor = Mock()
    processor.process(subtractionOperator) >> returnValue
    def result = subtractionOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    subtractionOperator.setPosition(position)

    then:
    subtractionOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    subtractionOperator.getName() == "-"
  }

}
