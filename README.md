# RPN Calculator

### How to build
In project directory: 
* MAC
```sh
$ ./gradlew clean build
```
* Windows
```sh
$ gradlew.bat clean build
```
Artifact is built into build/libs folder

### Input

* Numbers as plain decimal strings
* Available operators are +, -, *, /, sqrt, undo, clear
* Example
```
5 2 -
```

### How to run
```
java -jar <path-to>/rpn-calculator.jar
```

### Assumptions
- Numbers accepted are formatted as plain decimal strings (ie. no engineering formatting)
- First leading spaces followed by valid input are trimmed from the command line as well as trailing spaces
- At most 10 decimal places are displayed, ROUND_HALF_UP strategy will be used where applicable
