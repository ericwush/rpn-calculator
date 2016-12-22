package com.example.calculator.process

import com.example.calculator.input.ParsedInput
import spock.lang.Specification

class CommandLineProcessorSpec extends Specification {

  CommandLineProcessor commandLineProcessor
  InputProcessor inputProcessor

  def setup() {
    inputProcessor = Mock()
    commandLineProcessor = new CommandLineProcessor(inputProcessor)
  }

  def cleanup() {
  }

  def "test invalid input"() {
    when:
    List<Optional<ParsedInput>> parsedInputs = Arrays.asList(Optional.empty())

    def result = commandLineProcessor.process(parsedInputs)

    then:
    result.isFailure()
    result.cause.getClass() == IllegalArgumentException
    result.cause.getMessage() == "position 1: invalid input"
  }

  def "test processor throws exception"() {
    when:
    ParsedInput parsedInput = Mock()
    parsedInput.accept(inputProcessor) >> { inputProcessor ->
      throw new RuntimeException("some error")
    }
    List<Optional<ParsedInput>> parsedInputs = Arrays.asList(Optional.of(parsedInput))

    def result = commandLineProcessor.process(parsedInputs)

    then:
    result.isFailure()
    result.cause.getClass() == RuntimeException
    result.cause.getMessage() == "some error"
  }

  def "test empty input"() {
    when:
    def result = commandLineProcessor.process(input)

    then:
    result.isFailure()
    result.cause.getClass() == IllegalStateException
    result.cause.getMessage() == "Unexpected empty inputs"

    where:
    input << [null, new ArrayList<>()]
  }

  def "test successful process"() {
    ParsedInput parsedInput = Mock()
    when:
    parsedInput.accept(inputProcessor) >> new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1)))
    List<Optional<ParsedInput>> parsedInputs = Arrays.asList(Optional.of(parsedInput))

    def result = commandLineProcessor.process(parsedInputs)

    then:
    result.isSuccess()
    result.get() == new LinkedList<BigDecimal>(Arrays.asList(new BigDecimal(1)))
    1 * parsedInput.setPosition(1)
  }

}
