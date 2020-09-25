package cz.ankach.configuration;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class ConfigReader {
    private final String filename;

    private final String [] properties = {
            Config.PROPERTY_APP,
            Config.PROPERTY_HANDLER,
            Config.PROPERTY_READER,
            Config.PROPERTY_TESTER,
            Config.PROPERTY_TESTER_STATE,
            Config.PROPERTY_VIEW,
            Config.PROPERTY_VIEW_STATE,
            Config.PROPERTY_THREADS,
            Config.PROPERTY_TIMES
    };

    public ConfigReader(String filename) {
        this.filename = filename;
    }

    /**
     * Method loads configuration from a file.
     *
     * @return Config or null
     * */
    public Config loadConfig() {
        File configFile = new File(filename);
        Config config = new Config();
        try {
            Scanner scanner = new Scanner(configFile);
            while (scanner.hasNextLine()) {
                String property = scanner.nextLine();
                addProperty(property, config);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return config;
    }

    /**
     * Method parse and adds property to the config.
     *
     * @param line - line with key and value
     * @param config - object which represents a configuration of the app.
     *
     * @throws IllegalArgumentException when there are problems with line parsing.
     * */
    private void addProperty(String line, Config config) {
        String [] property = line.split("[ ]+");
        if (property.length != 2) {
            throw new IllegalArgumentException("Invalid config file. Mistake is on line: " + line);
        }
        if (!isAllowedProperty(property[0])) {
            throw new IllegalArgumentException("Property: "+ property[0] + " is not allowed.");
        }
        config.addProperty(property[0], property[1]);
    }

    private boolean isAllowedProperty(String key) {
        return Arrays.asList(properties).contains(key);
    }
}
