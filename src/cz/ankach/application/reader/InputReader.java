package cz.ankach.application.reader;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.SolutionArgument;

import java.util.List;

public abstract class InputReader {
    protected String inputFilename;
    protected String solutionFileName;

    public InputReader(String inputFilename, String solutionFileName) {
        this.inputFilename = inputFilename;
        this.solutionFileName = solutionFileName;
    }

    public abstract List<InputArgument> readInput();
    public abstract List<SolutionArgument> readSolution();
}
