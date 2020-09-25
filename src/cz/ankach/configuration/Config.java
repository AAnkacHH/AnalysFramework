package cz.ankach.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  This class represents all configuration of an application.
 *  */
public class Config {

    public static final String PROPERTY_APP = "Application";
    public static final String PROPERTY_HANDLER = "Handler";
    public static final String PROPERTY_READER = "Reader";
    public static final String PROPERTY_TESTER = "Tester";
    public static final String PROPERTY_TESTER_STATE = "Tester_state";
    public static final String PROPERTY_VIEW = "View";
    public static final String PROPERTY_VIEW_STATE = "View_state";
    public static final String PROPERTY_THREADS = "Threads";
    public static final String PROPERTY_TIMES = "Times";

    private final Map<String, String> conf;

    public Config() {
        this.conf = new HashMap<>();
    }

    public void addProperty(String key, String value) {
        conf.put(key, value);
    }

    public String getProperty(String key) {
        return conf.get(key);
    }

    public String getApplicationClassName() {
        return getProperty(PROPERTY_APP);
    }

    public String getApplicationHandlerClassName() {
        return getProperty(PROPERTY_HANDLER);
    }

    public String getReaderClassName() {
        return getProperty(PROPERTY_READER);
    }

    public String getTesterClassName() {
        return getProperty(PROPERTY_TESTER);
    }

    public String getViewClassName() {
        return getProperty(PROPERTY_VIEW);
    }
}
