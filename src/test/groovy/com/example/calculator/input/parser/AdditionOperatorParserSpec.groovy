package com.example.calculator.input.parser

import spock.lang.Specification

class AdditionOperatorParserSpec extends Specification {

  AdditionOperatorParser additionOperatorParser

  def setup() {
    additionOperatorParser = new AdditionOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    additionOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse +"() {
    expect:
    additionOperatorParser.parse("+").isPresent()
  }

}
