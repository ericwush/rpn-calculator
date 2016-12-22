package com.example.calculator.process

import spock.lang.Specification

class StackHistorySpec extends Specification {

  StackHistory stackHistory

  def setup() {
    stackHistory = new StackHistory()
  }

  def cleanup() {
  }

  def "test get latest is empty"() {
    expect:
    stackHistory.getLatest() == new LinkedList()
  }

  def "test get latest"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1))))
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(2))))

    then:
    stackHistory.getLatest() == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(2)))
  }

  def "test pop and get latest"() {
    when:
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1))))
    stackHistory.add(new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(2))))

    then:
    stackHistory.popAndGetLatest() == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1)))
  }

  def "test get stack content"() {
    expect:
    stackHistory.getStackContent(stack) == result

    where:
    stack << [new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal(2))),
              new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal("2.00000"))),
              new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1), new BigDecimal("2.12345678912345")))]
    result << ["stack: 2 1", "stack: 2 1", "stack: 2.1234567891 1"]
  }

}
