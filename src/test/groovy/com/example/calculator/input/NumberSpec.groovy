package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class NumberSpec extends Specification {

  Number number
  InputProcessor processor

  def setup() {
    number = new Number(new BigDecimal(100))
  }

  def cleanup() {
  }

  def "test accept processor"() {
    when:
    processor = Mock()
    processor.process(number) >> returnValue
    def result = number.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    number.setPosition(position)

    then:
    number.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    number.getName() == "100"
  }

  def "test number"() {
    expect:
    number.number == new BigDecimal(100)
  }

}
