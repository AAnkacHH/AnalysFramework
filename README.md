# AnalysFramework
This is a small testing program, which takes your code and runs it.
It uses reflection and inheritance, so you should inherit your classes from its intefaces and abstract classes.
### Classes
- Application - a main class for your program.
- Handler - a class with implemented algorithm you want to test.
- entities:
  - InputArgument - an input object for your algorithm.
  - OutputArgument - an output object which program will generate.
  - SolutionArgument - an object which represents a solution for current input object.
- InputReader - a class whith allow u to read input. Provides 2 method, readInput() and readSolution().
- Tester - this class can take the result of your algorithm and compares it with the solution.
- CustomView - this class is useful for representing output data, you can have many different views at one program.
