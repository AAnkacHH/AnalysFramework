package cz.ankach.analyzer;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.entity.SolutionArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.configuration.Config;

import java.util.List;

public class Analyzer {

    private Application application;
    private InputReader reader;
    private Config config;

    public Analyzer(Application application, InputReader reader, Config config) {
        this.application = application;
        this.reader = reader;
        this.config = config;
    }

    public void start() {
        List<SolutionArgument> solutions = reader.readSolution();
        List<InputArgument> inputs = reader.readInput();
        List<OutputArgument> outputs = application.run(inputs);
    }
}
