package cz.ankach.application;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;

import java.util.List;

/**
 * This interface represent custom algorithm which will be called for solving a problem.
 * */
public interface Handler {
    OutputArgument handle(InputArgument input);
}
