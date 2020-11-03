package cz.ankach.application;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;

/**
 * This interface represent custom algorithm which will be called for solving a problem.
 * */
public interface Handler {
    /**
     * This method must runs your algorithm.
     * @param input - input object.
     * @return OutputArgument - result.
     * */
    OutputArgument handle(InputArgument input);
}
