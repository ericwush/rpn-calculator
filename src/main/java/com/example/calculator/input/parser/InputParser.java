package com.example.calculator.input.parser;

import com.example.calculator.input.ParsedInput;

import java.util.Optional;

public interface InputParser<T extends ParsedInput> {

    Optional<T> parse(String input);

}
