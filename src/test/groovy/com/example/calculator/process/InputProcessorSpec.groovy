package com.example.calculator.process

import com.example.calculator.input.AdditionOperator
import com.example.calculator.input.ClearOperator
import com.example.calculator.input.DivisionOperator
import com.example.calculator.input.MultiplicationOperator
import com.example.calculator.input.Number
import com.example.calculator.input.SquareRootOperator
import com.example.calculator.input.SubtractionOperator
import com.example.calculator.input.UndoOperator
import spock.lang.Specification

class InputProcessorSpec extends Specification {

  InputProcessor inputProcessor
  StackHistory stackHistory

  def setup() {
    stackHistory = new StackHistory()
    inputProcessor = new InputProcessor(stackHistory)
  }

  def cleanup() {
  }

  def "test process number"() {
    expect:
    inputProcessor.process(new Number(new BigDecimal(1))) == new LinkedList(Arrays.asList(new BigDecimal(1)))
  }

  def "test AdditionOperator exception"() {
    when:
    def operator = new AdditionOperator()
    operator.setPosition(position)
    inputProcessor.process(operator)

    then:
    IllegalStateException exception = thrown()
    exception.message == "operator <+> (position: $position): insufficient parameters".toString()

    where:
    position = new Random().nextInt(100)
  }

  def "test AdditionOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2))))

    then:
    inputProcessor.process(new AdditionOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(3)))
  }

  def "test SubtractionOperator exception"() {
    when:
    def operator = new SubtractionOperator()
    operator.setPosition(position)
    inputProcessor.process(operator)

    then:
    IllegalStateException exception = thrown()
    exception.message == "operator <-> (position: $position): insufficient parameters".toString()

    where:
    position = new Random().nextInt(100)
  }

  def "test SubtractionOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(5), new BigDecimal(2))))

    then:
    inputProcessor.process(new SubtractionOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(3)))
  }

  def "test MultiplicationOperator exception"() {
    when:
    def operator = new MultiplicationOperator()
    operator.setPosition(position)
    inputProcessor.process(operator)

    then:
    IllegalStateException exception = thrown()
    exception.message == "operator <*> (position: $position): insufficient parameters".toString()

    where:
    position = new Random().nextInt(100)
  }

  def "test MultiplicationOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(5), new BigDecimal(2))))

    then:
    inputProcessor.process(new MultiplicationOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(10)))
  }

  def "test DivisionOperator exception"() {
    when:
    def operator = new DivisionOperator()
    operator.setPosition(position)
    inputProcessor.process(operator)

    then:
    IllegalStateException exception = thrown()
    exception.message == "operator </> (position: $position): insufficient parameters".toString()

    where:
    position = new Random().nextInt(100)
  }

  def "test DivisionOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(5), new BigDecimal(10))))

    then:
    inputProcessor.process(new DivisionOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(2)))
  }

  def "test SquareRootOperator exception"() {
    when:
    def operator = new SquareRootOperator()
    operator.setPosition(position)
    inputProcessor.process(operator)

    then:
    IllegalStateException exception = thrown()
    exception.message == "operator <sqrt> (position: $position): insufficient parameters".toString()

    where:
    position = new Random().nextInt(100)
  }

  def "test SquareRootOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(9))))

    then:
    inputProcessor.process(new SquareRootOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(3)))
  }

  def "test UndoOperator"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(9))))
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(10))))

    then:
    inputProcessor.process(new UndoOperator()) == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(9)))
  }

  def "test ClearOperator"() {
    expect:
    inputProcessor.process(new ClearOperator()) == new LinkedList<BigDecimal>()
  }

}
