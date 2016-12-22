package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class DivisionOperatorSpec extends Specification {

  DivisionOperator divisionOperator
  InputProcessor processor

  def setup() {
    divisionOperator = new DivisionOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    when:
    processor = Mock()
    processor.process(divisionOperator) >> returnValue
    def result = divisionOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    divisionOperator.setPosition(position)

    then:
    divisionOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    divisionOperator.getName() == "/"
  }

}
