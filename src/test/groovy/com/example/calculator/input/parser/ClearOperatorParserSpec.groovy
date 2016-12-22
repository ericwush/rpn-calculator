package com.example.calculator.input.parser

import spock.lang.Specification

class ClearOperatorParserSpec extends Specification {

  ClearOperatorParser clearOperatorParser

  def setup() {
    clearOperatorParser = new ClearOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    clearOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse clear"() {
    expect:
    clearOperatorParser.parse(input).isPresent()

    where:
    input << ["clear", "CLEAR"]
  }

}
