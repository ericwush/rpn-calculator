package com.example.calculator.input.parser

import spock.lang.Specification

class UndoOperatorParserSpec extends Specification {

  UndoOperatorParser undoOperatorParser

  def setup() {
    undoOperatorParser = new UndoOperatorParser()
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    expect:
    undoOperatorParser.parse(input) == result

    where:
    input << [null, "a"]
    result << [Optional.empty(), Optional.empty()]
  }

  def "test parse undo"() {
    expect:
    undoOperatorParser.parse(input).isPresent()

    where:
    input << ["undo", "UNDO"]
  }

}
