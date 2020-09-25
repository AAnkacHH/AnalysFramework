package cz.ankach.testapp;

import cz.ankach.application.Application;
import cz.ankach.application.Handler;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.tester.Tester;

import java.util.List;

public class App extends Application {

    public App(Handler handler, Tester tester) {
        super(handler, tester);
    }

    @Override
    public List<OutputArgument> run(List<InputArgument> inputs) {
        System.out.println("running!");
        return null;
    }
}
