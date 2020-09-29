package cz.ankach.factory;

import cz.ankach.analyzer.Analyzer;
import cz.ankach.application.Application;
import cz.ankach.application.Handler;
import cz.ankach.application.entity.SolutionArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;
import cz.ankach.application.view.TesterView;
import cz.ankach.command.CLIArgumentHandler;
import cz.ankach.configuration.Config;
import cz.ankach.reflector.Reflector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for building application object using information from config.
 * */
public class AnalyzerFactory {

    private final Reflector reflector;

    public AnalyzerFactory(Reflector reflector) {
        this.reflector = reflector;
    }

    public Analyzer create(Config config, Map<String, String> arguments) {
        // reader creation
        InputReader reader = reflector.createReaderClass(
                config.getReaderClassName(),
                arguments.get(CLIArgumentHandler.INPUT_FILENAME),
                arguments.get(CLIArgumentHandler.SOLUTION_FILENAME)
        );
        // tester creation
        Map<Integer, SolutionArgument> solutions = reader.readSolution();
        Tester tester = reflector.createTester(
                config.getTesterClassName(),
                Boolean.parseBoolean(config.getProperty(Config.PROPERTY_TESTER_STATE)),
                solutions
        );
        // application creation
        Handler handler = reflector.createHandler(config.getApplicationHandlerClassName());
        Application application = reflector.createAppObject(
                config.getApplicationClassName(),
                handler
        );
        // view creation
        List<String> viewClasses = config.getViewClassNames();
        List<CustomView> views = new LinkedList<>();
        for (String className: viewClasses) {
            views.add(reflector.createView(className));
        }
        TesterView testerView = new TesterView();

        return new Analyzer(application, reader, tester, config, testerView, views);
    }
}
