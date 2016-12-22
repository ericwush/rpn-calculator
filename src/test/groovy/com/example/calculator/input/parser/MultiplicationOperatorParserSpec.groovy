package com.example.calculator.input.parser

import spock.lang.Specification

class MultiplicationOperatorParserSpec extends Specification {

  MultiplicationOperatorParser multiplicationOperatorParser

  def setup() {
    multiplicationOperatorParser = new MultiplicationOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    multiplicationOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse *"() {
    expect:
    multiplicationOperatorParser.parse("*").isPresent()
  }

}
