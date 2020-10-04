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

### Configuration

Example configuration file: 
```
Application cz.ankach.application.App
Reader cz.ankach.reader.Reader
Tester cz.ankach.validator.Validator
Tester_enable true
Handler cz.ankach.application.ProblemHandler
View cz.ankach.view.FileView
View cz.ankach.view.TestView
Threads 1
Times 1
Scale 1000000
```
Times means how many times your program should be ran. For example if you set 5 thread and 20 times. The algorithm will reads the data 20 times and runs it in 5 threads. The framework is using thread pool.

Scale is how the framework should show cpu time. For example 1000000 - miliseconds, 1000000000 - seconds. The framework is measured the time in nanoseconds.

You can turn off the tester by setting parameter Tester_enabled to false.


### Adding to your project
You can easly add it to your project as a jar file.


### Running 
The framework takes a 3 arguments from command line, all of them are required:
- path to configuration file, the file name must ends .conf
- path to file with inputs.
- path to file with solutions for inputs.




