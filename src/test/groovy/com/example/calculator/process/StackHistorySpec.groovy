package com.example.calculator.process

import spock.lang.Specification

class StackHistorySpec extends Specification {

  StackHistory stackHistory

  def setup() {
    stackHistory = StackHistory.getInstance()
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
}
