package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class AdditionOperatorSpec extends Specification {

  AdditionOperator additionOperator

  def setup() {
    additionOperator = new AdditionOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    InputProcessor processor = Mock()
    when:
    processor.process(additionOperator) >> returnValue
    def result = additionOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    additionOperator.setPosition(position)

    then:
    additionOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    additionOperator.getName() == "+"
  }

}
