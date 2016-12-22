package com.example.calculator.input.parser

import spock.lang.Specification

class SubtractionOperatorParserSpec extends Specification {

  SubtractionOperatorParser subtractionOperatorParser

  def setup() {
    subtractionOperatorParser = new SubtractionOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    subtractionOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse -"() {
    expect:
    subtractionOperatorParser.parse("-").isPresent()
  }

}
