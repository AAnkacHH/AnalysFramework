package cz.ankach.factory;

import cz.ankach.application.Application;
import cz.ankach.application.Handler;
import cz.ankach.application.tester.Tester;
import cz.ankach.configuration.Config;
import cz.ankach.reflector.Reflector;

/**
 * This class is responsible for building application object using information from config.
 * */
public class ApplicationFactory {

    private final Reflector reflector;

    public ApplicationFactory(Reflector reflector) {
        this.reflector = reflector;
    }

    public Application build(Config config) {
        Tester tester = (Tester) reflector.createObject(config.getTesterClassName());
        Handler handler = (Handler) reflector.createObject(config.getApplicationHandlerClassName());

        return (Application) reflector.createAppObject(
                config.getApplicationClassName(),
                handler,
                tester
        );
    }
}
