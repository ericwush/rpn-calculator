package com.example.calculator.input.parser

import spock.lang.Specification

class DivisionOperatorParserSpec extends Specification {

  DivisionOperatorParser divisionOperatorParser

  def setup() {
    divisionOperatorParser = new DivisionOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    divisionOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse /"() {
    expect:
    divisionOperatorParser.parse("/").isPresent()
  }

}
