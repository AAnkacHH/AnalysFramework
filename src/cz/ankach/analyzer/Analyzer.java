package cz.ankach.analyzer;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.entity.SolutionArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.configuration.Config;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Map;

public class Analyzer {

    private final Application application;
    private final InputReader reader;
    private final Config config;

    public Analyzer(Application application, InputReader reader, Config config) {
        this.application = application;
        this.reader = reader;
        this.config = config;
    }

    public void start() {
        Map<Integer, SolutionArgument> solutions = reader.readSolution();
        List<InputArgument> inputs = reader.readInput();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = threadMXBean.getCurrentThreadCpuTime();
        List<OutputArgument> outputs = application.run(inputs);
        long end = threadMXBean.getCurrentThreadCpuTime();
        System.out.println(end - start);
    }
}
