package cz.ankach;

import cz.ankach.analyzer.Analyzer;
import cz.ankach.command.CLIArgumentHandler;
import cz.ankach.configuration.Config;
import cz.ankach.configuration.ConfigReader;
import cz.ankach.factory.AnalyzerFactory;
import cz.ankach.reflector.Reflector;

import java.util.Map;

public class Launcher {

    public static void main(String[] args) {
        CLIArgumentHandler argumentHandler = new CLIArgumentHandler();
        Map<String, String> arguments = argumentHandler.handleArguments(args);
        Config config = loadConfig(arguments);
        Reflector reflector = new Reflector();
        AnalyzerFactory factory = new AnalyzerFactory(reflector);

        Analyzer analyzer = factory.create(config, arguments);

        analyzer.start();
    }

    /**
     * Loads config.
     * */
    private static Config loadConfig(Map<String, String> arguments) {
        ConfigReader reader = new ConfigReader(arguments.get(CLIArgumentHandler.CONFIG_FILENAME));
        return reader.loadConfig();
    }
}
