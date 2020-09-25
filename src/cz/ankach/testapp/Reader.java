package cz.ankach.testapp;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.SolutionArgument;
import cz.ankach.application.reader.InputReader;

import java.util.List;

public class Reader extends InputReader {
    public Reader(String inputFilename, String solutionFileName) {
        super(inputFilename, solutionFileName);
    }

    @Override
    public List<InputArgument> readInput() {
        return null;
    }

    @Override
    public List<SolutionArgument> readSolution() {
        return null;
    }
}
