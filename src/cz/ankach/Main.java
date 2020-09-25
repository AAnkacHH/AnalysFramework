package cz.ankach;

import cz.ankach.analyzer.Analyzer;
import cz.ankach.application.Application;
import cz.ankach.application.reader.InputReader;
import cz.ankach.command.CLIArgumentHandler;
import cz.ankach.configuration.Config;
import cz.ankach.configuration.ConfigReader;
import cz.ankach.factory.ApplicationFactory;
import cz.ankach.reflector.Reflector;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CLIArgumentHandler argumentHandler = new CLIArgumentHandler();
        Map<String, String> arguments = argumentHandler.handleArguments(args);
        Config config = loadConfig(arguments);
        Reflector reflector = new Reflector();
        ApplicationFactory factory = new ApplicationFactory(reflector);
        InputReader reader = (InputReader) reflector.createReaderClass(
                config.getReaderClassName(),
                arguments.get(CLIArgumentHandler.INPUT_FILENAME),
                arguments.get(CLIArgumentHandler.SOLUTION_FILENAME)
        );
        Application application = factory.build(config);

        Analyzer analyzer = new Analyzer(application, reader, config);

        analyzer.start();
    }

    private static Config loadConfig(Map<String, String> arguments) {
        ConfigReader reader = new ConfigReader(arguments.get(CLIArgumentHandler.CONFIG_FILENAME));
        return reader.loadConfig();
    }
}
