package cz.ankach.application;

import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;

import java.util.List;

/**
 *  This class represents your application. Your main class must inherit form this class.
 *  */
public abstract class Application {

    protected Handler handler;

    public Application(Handler handler) {
        this.handler = handler;
    }

    /**
     * Method runs your algorithm. Application will be counting running time for this method.
     * @param inputs - input parameters of your algorithm.
     * @return List<OutputArgument> - this is results of algorithm counting.
     *  */
    public abstract List<OutputArgument> run(List<InputArgument> inputs, long scale, int count);
}
