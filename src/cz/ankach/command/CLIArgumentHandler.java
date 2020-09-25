package cz.ankach.command;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a handler which handles arguments form command line input.
 * */
public class CLIArgumentHandler {

    public static final String CONFIG_FILENAME = "config";
    public static final String INPUT_FILENAME = "input";
    public static final String SOLUTION_FILENAME = "solution";

    /**
     * Method gets arguments form command line input.
     *
     * @param args - arguments which are passed to main method.
     * @throws IllegalArgumentException when arguments are incorrect.
     * @return Map<String, String> where first is a key and second is a value.
     * */
    public Map<String, String> handleArguments(String[] args){
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid number of cli arguments.");
        }
        String configFileName = args[0];
        if (!configFileName.contains(".conf")) {
            throw new IllegalArgumentException("Invalid config file name.");
        }
        String inputFileName = args[1];
        String solutionFileName = args[2];
        Map<String, String> arguments = new HashMap<>();
        arguments.put(CONFIG_FILENAME, configFileName);
        arguments.put(INPUT_FILENAME, inputFileName);
        arguments.put(SOLUTION_FILENAME, solutionFileName);

        return arguments;
    }
}
