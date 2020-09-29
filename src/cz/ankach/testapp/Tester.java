package cz.ankach.testapp;

import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.entity.SolutionArgument;

import java.util.Map;

public class Tester extends cz.ankach.application.tester.Tester {


    public Tester(boolean enabled, Map<Integer, SolutionArgument> solutions) {
        super(enabled, solutions);
    }

    @Override
    public boolean test(OutputArgument outputArgument, SolutionArgument solution) {
        return false;
    }
}
