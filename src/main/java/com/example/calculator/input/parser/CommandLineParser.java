package com.example.calculator.input.parser;

import com.example.calculator.input.ParsedInput;

import java.util.Optional;

/**
 * I contain a chain of parsers and am responsible for parsing the command line input.
 */
public class CommandLineParser implements InputParser<ParsedInput> {

  private final InputParser[] parsers;

  public CommandLineParser(final InputParser... parsers) {
    this.parsers = parsers;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Optional<ParsedInput> parse(final String input) {
    for (final InputParser parser : parsers) {
      final Optional<ParsedInput> maybeParsedInput = parser.parse(input);
      if (maybeParsedInput.isPresent()) {
        return maybeParsedInput;
      }
    }
    return Optional.empty();
  }
}
