package cz.ankach.analyzer;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;
import cz.ankach.application.view.TesterView;
import cz.ankach.configuration.Config;

import java.util.List;

public class Analyzer {

    private final Application application;
    private final InputReader reader;
    private final Config config;
    private final Tester tester;
    private final TesterView testerView;
    private final List<CustomView> views;

    public Analyzer(
            Application application,
            InputReader reader,
            Tester tester,
            Config config,
            TesterView testerView,
            List<CustomView> views
    ) {
        this.application = application;
        this.reader = reader;
        this.config = config;
        this.tester = tester;
        this.testerView = testerView;
        this.views = views;
    }

    /**
     * This method creates a thread pool and runs tasks.
     * */
    public void start() {
        int times = Integer.parseInt(config.getProperty(Config.PROPERTY_TIMES).trim());
        int scale = Integer.parseInt(config.getProperty(Config.PROPERTY_SCALE));

        List<InputArgument> inputs = reader.readInput();
        List<OutputArgument> outputs = application.run(inputs, scale, times);

        if (tester.isEnabled()) {
            List<String> errors = tester.testAll(outputs);
            testerView.showErrors(errors);
        }
        for (CustomView view : views) {
            view.print(outputs);
        }
    }
}
