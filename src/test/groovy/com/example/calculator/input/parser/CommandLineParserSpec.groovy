package com.example.calculator.input.parser

import com.example.calculator.input.ParsedInput
import spock.lang.Specification

class CommandLineParserSpec extends Specification {

  CommandLineParser commandLineParser
  InputParser inputParser1
  InputParser inputParser2
  InputParser inputParser3

  def setup() {
    inputParser1 = Mock()
    inputParser2 = Mock()
    inputParser3 = Mock()
    commandLineParser = new CommandLineParser(inputParser1, inputParser2, inputParser3)
  }

  def cleanup() {
  }

  def "test parse get empty"() {
    when:
    inputParser1.parse("input") >> Optional.empty()
    inputParser2.parse("input") >> Optional.empty()
    inputParser3.parse("input") >> Optional.empty()

    then:
    commandLineParser.parse("input") == Optional.empty()
  }

  def "test parse get ParsedInput"() {
    when:
    ParsedInput parsedInput = Mock()
    inputParser1.parse("input") >> Optional.empty()
    inputParser2.parse("input") >> Optional.of(parsedInput)
    def result = commandLineParser.parse("input")

    then:
    result == Optional.of(parsedInput)
    0 * inputParser3.parse("input")
  }

}
