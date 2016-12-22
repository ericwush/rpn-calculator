package com.example.calculator.process

import javaslang.control.Either
import spock.lang.Specification

class ResultViewerSpec extends Specification {

  ResultViewer resultViewer
  StackHistory stackHistory

  def setup() {
    stackHistory = Mock()
    resultViewer = new ResultViewer(stackHistory)
  }

  def cleanup() {
  }

  def "test print stack"() {
    when:
    def result = Either.right(new LinkedList(Arrays.asList(new BigDecimal(1))))
    resultViewer.printResult(result)

    then:
    1 * stackHistory.getStackContent(new LinkedList(Arrays.asList(new BigDecimal(1))))
  }

  def "test print error"() {
    RuntimeException exception = Mock()
    when:
    def result = Either.left(exception)
    resultViewer.printResult(result)

    then:
    1 * exception.getMessage()
    1 * stackHistory.getStackContent(stackHistory.getLatest())
  }

}
