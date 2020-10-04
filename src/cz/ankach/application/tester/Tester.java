package cz.ankach.application.tester;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.entity.SolutionArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Tester {
    protected boolean enabled = false;
    protected Map<Integer, SolutionArgument> solutions;

    public Tester(boolean enabled, Map<Integer, SolutionArgument> solutions) {
        this.enabled = enabled;
        this.solutions = solutions;
    }

    public boolean isEnabled() {return enabled;}

    public abstract boolean test(OutputArgument outputArgument, SolutionArgument solution);

    public List<String> testAll(List<OutputArgument> arguments) {
        List<String> errors = new ArrayList<>();
        for (OutputArgument arg: arguments) {
            InputArgument input = arg.getReferenceInput();
            SolutionArgument solution = solutions.get(input.getId());

            if (!test(arg, solution)) {
                errors.add("Incorrect solution for input ''': " + input.getId());
            }
        }
        return errors;
    }
}
