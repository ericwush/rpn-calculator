package com.example.calculator.input

import com.example.calculator.process.InputProcessor
import spock.lang.Specification

class UndoOperatorSpec extends Specification {

  UndoOperator undoOperator

  def setup() {
    undoOperator = new UndoOperator()
  }

  def cleanup() {
  }

  def "test accept processor"() {
    InputProcessor processor = Mock()
    when:
    processor.process(undoOperator) >> returnValue
    def result = undoOperator.accept(processor)

    then:
    result == returnValue

    where:
    returnValue = new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2)))
  }

  def "test position"() {
    when:
    undoOperator.setPosition(position)

    then:
    undoOperator.getPosition() == position

    where:
    position = new Random().nextInt(100)
  }

  def "test name"() {
    expect:
    undoOperator.getName() == "undo"
  }

}
