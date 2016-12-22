package com.example.calculator.input.parser

import spock.lang.Specification

class SquareRootOperatorParserSpec extends Specification {

  SquareRootOperatorParser squareRootOperatorParser

  def setup() {
    squareRootOperatorParser = new SquareRootOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    squareRootOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse sqrt"() {
    expect:
    squareRootOperatorParser.parse(input).isPresent()

    where:
    input << ["sqrt", "SQRT"]
  }

}
